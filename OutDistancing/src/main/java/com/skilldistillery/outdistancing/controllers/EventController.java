package com.skilldistillery.outdistancing.controllers;

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
import com.skilldistillery.outdistancing.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class EventController {

	// COME BACK AND CHECK RECURSION

	@Autowired
	private EventService eventSvc;

	@GetMapping("events")
	public List<Event> index() {
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

	@PostMapping("activities/{actId}/events")
	@ResponseBody
	public Event addEvent(@PathVariable("actId") int actId, @RequestBody Event event, HttpServletRequest request,
			HttpServletResponse resp) {

		// hardcoded user 1, fix with spring security
		String hardcodedUsername = "kissmyaxe";

		try {
			Event addedEvent = eventSvc.createEvent(event, actId, hardcodedUsername);
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

	@PutMapping("activities/{actId}/events/{evtId}")
	public Event updateEvent(@PathVariable("evtId") Integer evtId, @PathVariable("actId") Integer actId,
			@RequestBody Event event, HttpServletResponse resp) {
		String hardcodedUsername = "kissmyaxe";
		try {
			event = eventSvc.updateEvent(evtId, event, hardcodedUsername);
			if (event == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			event = null;
		}
		return event;
	}

	@DeleteMapping("activities/{actId}/events/{evtId}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("evtId") Integer evtId,
			@PathVariable("actId") Integer actId) {
//		boolean isEnabled = eventSvc.changeEventEnabled(evtId);
		try {
			if (eventSvc.deleteById(evtId)) {
				res.setStatus(200);
				return true;
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(409);
		}
		return false;
	}

}
