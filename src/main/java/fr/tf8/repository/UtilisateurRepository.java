package fr.tf8.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tf8.user.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByEmail(String email);
	
	  Boolean existsByEmail(String email);
}
