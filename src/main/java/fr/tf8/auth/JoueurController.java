package fr.tf8.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {

	@GetMapping("/quizz")
	public String getQuizzList() {
		return "ACCES JOUEUR REUSSI";
	}
}
