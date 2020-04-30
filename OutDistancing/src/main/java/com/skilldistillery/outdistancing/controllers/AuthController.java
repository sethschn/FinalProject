package com.skilldistillery.outdistancing.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.services.AuthService;
import com.skilldistillery.outdistancing.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4220" })
public class AuthController {
	@Autowired
	private AuthService svc;
	
	@Autowired
	private UserService userSvc;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user, HttpServletResponse response) {
		if (user == null) {
			response.setStatus(400);
		}

		user = svc.register(user);

		return user;
	}

	@GetMapping("/authenticate")
	public User authenticate(Principal principal) {
		return userSvc.findUserByUsername(principal.getName());
		//return principal;
	}


}
