package fr.tf8.quiz.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fr.tf8.quiz.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  // On récupère la clé secrète définie dans application.properties
  @Value("${application.security.jwt.secret-key}")
  private String jwtSecret;

  // On récupère la durée de validité (24h)
  @Value("${application.security.jwt.expiration}")
  private int jwtExpirationMs;

  /**
   * 1. FABRIQUER LE BADGE
   * Cette méthode est appelée après un login réussi.
   * Elle génère le token avec l'email de l'utilisateur dedans.
   */
  public String generateJwtToken(Authentication authentication) {

	  UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername())) // On met l'email dans le token
        .setIssuedAt(new Date()) // Date de création
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Date d'expiration
        .signWith(key(), SignatureAlgorithm.HS256) // Signature cryptographique
        .compact();
  }
  
  /**
   * Méthode utilitaire pour décoder la clé secrète
   */
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  /**
   * 2. LIRE LE BADGE
   * Récupère l'email caché à l'intérieur du token.
   */
  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * 3. VÉRIFIER LE BADGE
   * Vérifie si le token est valide, non expiré et bien signé par nous.
   */
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Token JWT invalide: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("Token JWT expiré: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("Token JWT non supporté: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("La chaîne claims JWT est vide: {}", e.getMessage());
    }

    return false;
  }
}