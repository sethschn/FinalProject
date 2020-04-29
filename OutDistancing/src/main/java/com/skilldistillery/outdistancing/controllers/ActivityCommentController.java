package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.ActivityComment;
import com.skilldistillery.outdistancing.services.ActivityCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ActivityCommentController {

	@Autowired
	private ActivityCommentService comSvc;

	// find all
	@GetMapping("activitycomments")
	public List<ActivityComment> findAll() {
		return comSvc.findAll();
	}

	// find by id
	@GetMapping("activitycomments/{id}")
	public ActivityComment findById(@PathVariable Integer id, HttpServletResponse response) {
		ActivityComment comment = comSvc.findById(id);
		if (comment == null) {
			response.setStatus(404);
		}
		return comment;
	}
	
	//create activity comment
		@PostMapping("activities/{actId}/activitycomments")
		public ActivityComment createActivityComment(@RequestBody ActivityComment comment, @PathVariable("actId")int actId, HttpServletRequest request,
				HttpServletResponse response) {
			try {
				ActivityComment newComment = comSvc.addActivityComment(comment, actId);
				response.setStatus(201);
				StringBuffer url = request.getRequestURL();
				url.append("/").append(comment.getId());
				String location = url.toString();
				response.addHeader("Location", location);
				return newComment;
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(400);
				return null;
			}
		}
		
		// updated activity comment
		@PutMapping("activitycomments/{id}")
		public ActivityComment updateFlight(@PathVariable Integer id, @RequestBody ActivityComment comment) {
			ActivityComment updatedComment = comSvc.updateActivityComment(comment, id);
			return updatedComment;
		}

}
