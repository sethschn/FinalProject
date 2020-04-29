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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//find all
	@GetMapping("activities")
	public List <Activity> findAll(HttpServletRequest req, HttpServletResponse res){
		return activitySvc.findAll();
	}
	
	//find by id
	@GetMapping("activities/{id}")
	public Activity findById(@PathVariable Integer id, HttpServletResponse response) {
		Activity workout = activitySvc.findById(id);
		if (workout == null) {
			response.setStatus(404);
		}
		return workout;
	}
	
	//create activity
	@PostMapping("activities")
	public Activity createWorkout(@RequestBody Activity activity, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Activity newActivity = activitySvc.addActivity(activity);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(activity.getId());
			String location = url.toString();
			response.addHeader("Location", location);
			return newActivity;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
	}
	
	// updated activity
		@PutMapping("activities/{id}")
		public Activity updateFlight(@PathVariable Integer id, @RequestBody Activity activity) {
			Activity updatedActivity = activitySvc.updateActivity(activity, id);
			System.out.println(updatedActivity);
			return updatedActivity;
		}
		
	// disable activity
		@DeleteMapping("activities/{title}")
		public void deactivateActivity(@PathVariable String title, HttpServletResponse resp, Principal principal) {
			if (activitySvc.changeActivityEnabled(title)) {
	            resp.setStatus(200);
	        } else {
	            resp.setStatus(404);
	        }
	    } 
		
		
		
	
	

}
