package fr.tf8.quiz.security.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.tf8.quiz.security.services.UserDetailsServiceImpl;



public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService; 

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      // 1. On récupère le token JWT depuis l'en-tête "Authorization"
      String jwt = parseJwt(request);

      // 2. Si le token existe et qu'il est valide
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        
        // 3. On extrait l'email de l'utilisateur
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        // 4. On charge les détails de l'utilisateur depuis la BDD
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 5. On crée l'objet d'authentification officiel de Spring
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 6. On dit à Spring Security : "C'est bon, il est connecté !"
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Impossible de définir l'authentification utilisateur: {}", e);
    }

    // On laisse la requête continuer son chemin vers le contrôleur
    filterChain.doFilter(request, response);
  }

  // Petite méthode utilitaire pour nettoyer le header (enlever "Bearer ")
  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }
}