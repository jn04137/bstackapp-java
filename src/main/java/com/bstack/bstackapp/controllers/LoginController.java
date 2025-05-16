package com.bstack.bstackapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bstack.bstackapp.models.UserAccount;
import com.bstack.bstackapp.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepo;


	@Autowired
	private PasswordEncoder passwordEncoder;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);

			HttpSession session = httpRequest.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

			httpRequest.getSession(true);
			return ResponseEntity.ok("Login successful");
		} catch(AuthenticationException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest) {
		if(userRepo.findUserByUsername(loginRequest.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
		}

		UserAccount user = new UserAccount();
		user.setUsername(loginRequest.getUsername());
		user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		user.setEmail(loginRequest.getEmail());

		userRepo.save(user);

		return ResponseEntity.ok("User created");
	}
}


class LoginRequest {
	private String username;
	private String password;
	private String email;

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}
}
