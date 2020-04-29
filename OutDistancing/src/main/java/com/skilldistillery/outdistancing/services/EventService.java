package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Event;

public interface EventService {
	
	List<Event> findAll();

	Event findById(int evtId);

	Event createEvent(Event event);

	Event updateEvent(int evtId , Event event);

	boolean deleteById(int evtId);

}