package com.skilldistillery.outdistancing.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({ "*", "http://localhost:4220" })
public class TestController {
	
	@GetMapping("ping")
	public String ping() {
		return "pong";
	}
}
