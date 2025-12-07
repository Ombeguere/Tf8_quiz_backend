package fr.tf8.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indique à Spring que c'est une table SQL
@Table(name = "utilisateurs") // Nom de la table dans MySQL
@Data // Lombok : Génère les Getters et Setters automatiquement
@NoArgsConstructor // Lombok : Génère le constructeur vide
@AllArgsConstructor // Lombok : Génère le constructeur avec tous les champs
public class Utilisateur {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

    // Règle DSL: NOM, VARCHAR 20, NN (Not Null)
       @Column(length = 20, nullable = false)
       private String nom;

    // Règle DSL: PRENOM, VARCHAR 20, NN
       @Column(length = 20, nullable = false)
       private String prenom;

    // Règle DSL: MAIL, VARCHAR 50, NN, UNIQUE
    // C'est votre identifiant de connexion
       @Column(length = 50, nullable = false, unique = true)
       private String email;

    // Règle DSL: MOT DE PASSE, NN
    // NOTE TECHNIQUE : Le DSL demande 35 char, mais un mot de passe crypté (haché)
    // prend souvent 60 caractères. On met 120 pour être sûr de ne pas bloquer l'inscription.
       @Column(length = 120, nullable = false)
       private String password;

    // Règle DSL Rôles (ADMIN, ANIMATEUR, JOUEUR)
    // On stockera le rôle sous forme de texte ex: "ROLE_JOUEUR"
       @Column(nullable = false)
       private Integer role;
       
       
    
    // Constructeur personnalisé pratique pour l'inscription
    public Utilisateur(String nom, String prenom, String email, String password, Integer role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}