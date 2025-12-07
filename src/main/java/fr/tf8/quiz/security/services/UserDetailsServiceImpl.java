package fr.tf8.quiz.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tf8.quiz.model.Utilisateur;
import fr.tf8.quiz.repository.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UtilisateurRepository utilisateurRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   // 1. Recherche de l'utilisateur dans la BDD par son email
    Utilisateur user = utilisateurRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

    // 2. Conversion de l'Utilisateur BDD vers l'Utilisateur Sécurité (UserDetails)
    return UserDetailsImpl.build(user);
  }
  
 
}