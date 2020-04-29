package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.services.UserService;

@RestController
@RequestMapping ("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class UserController {
	
	@Autowired
	UserService userSvc;

	@GetMapping("users")
	public List<User> listUsers() {
		return userSvc.listAllUsers();
	}
	
	@GetMapping("users/{userId}")
	public User showById(@PathVariable("userId") int id, HttpServletResponse response) {
		User user = userSvc.findById(id);
		if (user == null) {
			response.setStatus(404);
		}
		return user;
	}
	
	
	 
}
