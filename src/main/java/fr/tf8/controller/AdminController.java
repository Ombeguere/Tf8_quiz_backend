package fr.tf8.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AdminController {
    
    @GetMapping("/questions/list")
    public String listQuestions() {
        return "Accès ADMIN : Liste de toutes les questions de la banque.";
    }

    @PostMapping("/quiz/{quizId}/questions")
    public String addQuizQuestion(@PathVariable Long quizId) {
        return "Accès ADMIN : Ajout d'une question au quiz ID " + quizId;
    }

    @PostMapping("/quiz/{quizId}/control/start")
    public String startQuiz(@PathVariable Long quizId) {
        return "Accès ADMIN : Démarrage du quiz ID " + quizId;
    }
    
    @DeleteMapping("/quiz/{quizId}/admin/reset")
    public String resetQuiz(@PathVariable Long quizId) {
        return "Accès ADMIN : Reset des scores du quiz ID " + quizId;
    }
}