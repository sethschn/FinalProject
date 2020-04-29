package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class EventController {
	
	//COME BACK AND CHECK RECURSION
	
	@Autowired
	private EventService eventSvc;
	
	@GetMapping("events")
	public List<Event> index(){
		return eventSvc.findAll();
	}
	
	@GetMapping("events/{evtId}")
	public Event showById(@PathVariable("evtId") Integer id, HttpServletResponse response) {
		Event event = eventSvc.findById(id);
		if (event == null) {
			response.setStatus(404);
		}
		return event;
	}
	
	@PostMapping("events")
	@ResponseBody
	public Event addEvent(@RequestBody Event event, HttpServletRequest request,
			HttpServletResponse resp) {
		try {
			Event addedEvent= eventSvc.createEvent(event);
			resp.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(event.getId());
			String location = url.toString();
			resp.addHeader("Location", location);
			return addedEvent;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
	}

	
	
}
