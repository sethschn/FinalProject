package com.skilldistillery.outdistancing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RefreshController {
	
	@GetMapping({
		"login",
		"logout",
		"profile", 
		"contact",
		"about",
		"event",
		"activities",
		"resources",
		"locations",
		"users",
		"eventDetail",
		"resourceDetail",
		"locationDetail",
		"nonUserLanding",
		"userLanding",
		"register",
		"admin",
		"groups"	
	})
	public String home() {
		return "index.html";
	}
	

	

}
