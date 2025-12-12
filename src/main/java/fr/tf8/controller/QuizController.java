package fr.tf8.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")	
public class QuizController {

    @GetMapping
    public String getAllQuizzes() {
        return "Accès public : Liste de tous les quiz.";
    }

    @GetMapping("/{quizId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getQuizDetails(@PathVariable Long quizId) {
        return "Accès ADMIN : Détails du quiz ID " + quizId;
    }

    @GetMapping("/join")
    public String joinQuiz() {
        return "Accès Authentifié : Un joueur peut rejoindre un quiz.";
    }
}