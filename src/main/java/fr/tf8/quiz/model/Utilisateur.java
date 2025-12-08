package fr.tf8.quiz.model;

import jakarta.persistence.*;

/**
 * Représente un utilisateur de l'application de Quiz TF8.
 * 
 * Cette classe est une entité JPA mappée sur la table "utilisateurs" de la base de données.
 * Elle stocke les informations d'identification et de profil de chaque participant
 * (Joueur, Animateur ou Administrateur).
 *
 */
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    /**
     * Identifiant unique de l'utilisateur.
     * Généré automatiquement par la base de données (Auto-incrément).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de famille de l'utilisateur.
     * Ne peut pas être nul et limité à 20 caractères.
     */
    @Column(length = 20, nullable = false)
    private String lastname;

    /**
     * Prénom de l'utilisateur.
     * Ne peut pas être nul et limité à 20 caractères.
     */
    @Column(length = 20, nullable = false)
    private String firstname;

    /**
     * Adresse email de l'utilisateur.
     * <p>
     * Sert d'identifiant de connexion (Login).
     * Doit être unique dans la base de données.
     * </p>
     */
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    /**
     * Mot de passe de l'utilisateur.
     * <p>
     * Stocké sous forme chiffrée (hash).
     * La longueur est fixée à 120 pour accommoder les hashs de sécurité (ex: BCrypt).
     * </p>
     */
    @Column(length = 255, nullable = false)
    private String password;

    /**
     * Rôle de l'utilisateur défini par un entier.
     * <p>
     * Les valeurs correspondent aux constantes définies dans {@link RoleConstants} :
     * <ul>
     * <li>1 : Joueur</li>
     * <li>2 : Animateur</li>
     * <li>3 : Administrateur</li>
     * </ul>
     * </p>
     */
    @Column(nullable = false)
    private int role;

    /**
     * Constructeur par défaut.
     * <p>
     * Requis par la spécification JPA.
     * </p>
     */
    public Utilisateur() {}

    /**
     * Constructeur complet pour créer un nouvel utilisateur.
     *
     * @param lastname      Le nom de famille de l'utilisateur.
     * @param firstname   Le prénom de l'utilisateur.
     * @param email    L'adresse email (doit être unique).
     * @param password Le mot de passe (doit être chiffré avant d'être passé ici).
     * @param role     L'identifiant du rôle (voir {@link RoleConstants}).
     */
    public Utilisateur(String lastname, String firstname, String email, String password, int role) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //GETTERS ET SETTERS

    /**
     * Récupère l'identifiant unique de l'utilisateur.
     * @return L'ID de l'utilisateur.
     */
    public Long getId() { return id; }

    /**
     * Définit l'identifiant de l'utilisateur.
     * @param id Le nouvel ID.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Récupère le nom de famille.
     * @return Le nom.
     */
    public String getLastName() { return lastname; }

    /**
     * Définit le nom de famille.
     * @param lastname .
     */
    public void setLastName(String lastname) { this.lastname = lastname; }

    /**
     * Récupère le prénom.
     * @return the new  firstName.
     */
    public String getFirstName() { return firstname; }

    /**
     * Définit le prénom.
     * @param firstName the new firstName.
     */
    public void setFirstName(String firstname) { this.firstname = firstname; }

    /**
     * Récupère l'adresse email.
     * @return L'email.
     */
    public String getEmail() { return email; }

    /**
     * Définit l'adresse email.
     * @param email Le nouvel email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Récupère le mot de passe chiffré.
     * @return Le mot de passe hashé.
     */
    public String getPassword() { return password; }

    /**
     * Définit le mot de passe.
     * @param password Le nouveau mot de passe (doit être déjà chiffré).
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Récupère l'identifiant numérique du rôle.
     * @return L'entier représentant le rôle (1, 2 ou 3).
     */
    public int getRole() { return role; }

    /**
     * Définit le rôle de l'utilisateur.
     * @param role L'entier représentant le nouveau rôle.
     */
    public void setRole(int role) { this.role = role; }
}

























/*
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

	

	
	
}

*/