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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.EventComment;
import com.skilldistillery.outdistancing.services.EventCommentService;
import com.skilldistillery.outdistancing.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class EventCommentController {

	// COME BACK AND CHECK RECURSION

	@Autowired
	private EventCommentService eventCmtSvc;

	@GetMapping("eventcomments")
	public List<EventComment> index() {
		return eventCmtSvc.findAll();
	}

	@GetMapping("eventcomments/{eventCmtId}")
	public EventComment showById(@PathVariable("eventCmtId") Integer id, HttpServletResponse resp) {
		EventComment evtCmt = eventCmtSvc.findById(id);
		if (evtCmt == null) {
			resp.setStatus(404);
		}
		return evtCmt;
	}

	@PostMapping("events/{evtId}/eventcomments")
	public EventComment addEventComment(
			@PathVariable("evtId") Integer evtId, 
			@RequestBody EventComment eventCmt,
			HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		
		try {
			EventComment addEventCmt = eventCmtSvc.createEventComment(eventCmt, evtId, principal.getName());
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(eventCmt.getId());
			String location = url.toString();
			resp.addHeader("Location", location);
			return addEventCmt;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("events/{evtId}/eventcomments/{evtCmtId}")
	public EventComment updateEventComment(
			@RequestBody EventComment eventCmt,
			@PathVariable("evtId") Integer eventId,
			@PathVariable("evtCmtId") Integer evtCmtId,
			HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		eventCmt = eventCmtSvc.updateEventComment(evtCmtId, eventCmt, principal.getName());
		
		
		return null;
	}

}
