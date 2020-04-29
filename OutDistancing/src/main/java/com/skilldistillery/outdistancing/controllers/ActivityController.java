package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.services.ActivityService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ActivityController {
	
	@Autowired
	private ActivityService activitySvc;
	
	@GetMapping("activities")
	public List <Activity> findAll(){
		return activitySvc.findAll();
	}
	

}
