package fr.tf8.quiz.security.services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.tf8.quiz.model.RoleConstants;
import fr.tf8.quiz.model.Utilisateur;
import lombok.Data; // Si vous utilisez Lombok, sinon gardez vos getters manuels


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;

    // RECTIFICATION : Le constructeur doit porter le nouveau nom
    public UserDetailsImpl(Long id, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

public static UserDetailsImpl build(Utilisateur user) {
        
        // On traduit le chiffre en texte pour Spring Security
        String roleName;
        
        // On vérifie la valeur du int role
        if (user.getRole() == RoleConstants.ADMIN) {
             roleName = "ROLE_ADMIN";
        } else if (user.getRole() == RoleConstants.ANIMATEUR) {
             roleName = "ROLE_ANIMATEUR";
        } else {
             roleName = "ROLE_JOUEUR"; // Par défaut (1)
        }

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(roleName));

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // IMPORTANT : L'email sert de login 
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
    
    public Long getId() { return id; }
    public String getEmail() { return email; }
}