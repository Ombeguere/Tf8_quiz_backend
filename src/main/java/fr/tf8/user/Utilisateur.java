package fr.tf8.user;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", length = 255)
	private String email;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "lastname", length = 100)
	private String lastname;

	@Column(name = "firstname", length = 100)
	private String firstname;

	@Column(name = "role")
	private Integer role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roleName;
		switch (this.role) {
		case 1:
			roleName = "ROLE_ADMIN";
			break;
		case 2:
			roleName = "ROLE_ANIMATEUR";
			break;
		default:
			roleName = "ROLE_JOUEUR";
		}
		return List.of(new SimpleGrantedAuthority(roleName));
	}

	@Override
	public @Nullable String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
