package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class EventController {
	
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

}
