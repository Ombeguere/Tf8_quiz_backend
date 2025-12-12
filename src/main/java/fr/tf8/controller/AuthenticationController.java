package fr.tf8.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tf8.auth.AuthenticationRequest;
import fr.tf8.auth.AuthenticationResponse;
import fr.tf8.auth.AuthenticationService;
import fr.tf8.auth.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private final AuthenticationService service;

	public AuthenticationController(AuthenticationService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}
}