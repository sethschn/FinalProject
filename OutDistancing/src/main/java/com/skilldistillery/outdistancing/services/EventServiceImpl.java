package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepo;

	@Override
	public List<Event> findAll() {
		return eventRepo.findAll();
	}

	@Override
	public Event findById(int evtId) {
		Optional<Event> optEvent = eventRepo.findById(evtId);
		Event event = null;
		if (optEvent.isPresent()) {
			event = optEvent.get();
		} else {
			return null;
		}
		return event;
	}
	
	@Override
	public Event createEvent(Event event) {
		try {
			event = eventRepo.saveAndFlush(event);
			return event;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Event updateEvent(int evtId, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(int evtId) {
		// TODO Auto-generated method stub
		return false;
	}

}
