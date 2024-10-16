package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		userService.signUp(user);
		return ResponseEntity.ok("User registered successfully");
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signIn(@RequestBody String name, @RequestParam String password) {
		try {
			userService.signIn(name, password);
			return ResponseEntity.ok("Login successfull");
		} catch (Exception e ) {
			return ResponseEntity.badRequest().body("Invalid credentials");
		}
	}

}
