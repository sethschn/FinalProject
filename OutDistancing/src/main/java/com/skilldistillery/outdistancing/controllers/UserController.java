package com.skilldistillery.outdistancing.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.services.EventService;
import com.skilldistillery.outdistancing.services.UserService;

@RestController
@RequestMapping ("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class UserController {
	
	@Autowired
	private UserService userSvc;
	
	

	@GetMapping("users")
	public List<User> listUsers() {
		return userSvc.listAllUsers();
	}
	
	@GetMapping("users/{userId}")
	public User showById(@PathVariable("userId") int id, HttpServletResponse response, Principal principal) {
		User user = userSvc.findById(id, principal.getName());
		if (user == null) {
			response.setStatus(404);
		}
		return user;
	}
	
	
//	@PostMapping("users")
//	@ResponseBody
//	public User createNewUser(@RequestBody User user, HttpServletRequest request,
//			HttpServletResponse response) {
//		
//		try {
//			User addUser = userSvc.createUser(user);
//			response.setStatus(201);
//			StringBuffer url = request.getRequestURL();
//			url.append("/").append(user.getId());
//			String location = url.toString();
//			response.addHeader("Location", location);
//			return addUser;
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setStatus(400);
//			return null;
//		}
//	}
	
	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User user,
			HttpServletResponse resp, Principal principal) {
		try {
			user = userSvc.updateUser(userId, user, principal.getName());
			if (user == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			user = null;
		}
		return user;
	}

	
	@DeleteMapping("users/{userId}")
	public boolean deactivateUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		boolean isEnabled = userSvc.changeUserEnabled(userId, principal.getName());
		if (isEnabled) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return isEnabled;
	}
	
	@PutMapping("users/{userId}/events/{eventId}")
	public User addUserEvent(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @PathVariable int eventId, Principal principal) {
		User user = userSvc.findUserByUsername(principal.getName());
		try {
			userSvc.addUserEvent(eventId, user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}
	
	@DeleteMapping("users/{userId}/events/{eventId}")
	public User removeUserEvent(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @PathVariable int eventId, Principal principal) {
		User user = userSvc.findUserByUsername(principal.getName());
		try {
			userSvc.removeUserEvent(eventId, user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}
	 
}
