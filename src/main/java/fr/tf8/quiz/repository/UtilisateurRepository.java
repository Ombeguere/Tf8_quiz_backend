package fr.tf8.quiz.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.tf8.quiz.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     *
     * Spring comprend "findByEmail" et écrit tout seul le SQL :
     * "SELECT * FROM utilisateurs WHERE email = ?"
     */
    Optional<Utilisateur> findByEmail(String email);

    /**
     * Utile pour l'inscription : permet de vérifier si un email est déjà pris.
     * Renvoie VRAI si l'email existe, FAUX sinon.
     */
    Boolean existsByEmail(String email);
}