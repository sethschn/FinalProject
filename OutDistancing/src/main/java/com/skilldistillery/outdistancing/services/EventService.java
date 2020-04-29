package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Event;

public interface EventService {
	
	List<Event> findAll();

	Event findById(int evtId);

	Event createEvent(Event event, int actId, String username);

	Event updateEvent(int evtId , Event event, String username);

//	boolean disable();

}
