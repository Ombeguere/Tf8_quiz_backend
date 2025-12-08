package fr.tf8.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

 @GetMapping("/info")
 public String getAdminInfo() {
     return "ACCES ADMIN REUSSI";
 }
}