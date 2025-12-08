package fr.tf8.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animateur")
public class AnimateurController {

	@GetMapping("/quizz")
	public String getQuizzList() {
		return "ACCES ANIMATEUR REUSSI";
	}
}