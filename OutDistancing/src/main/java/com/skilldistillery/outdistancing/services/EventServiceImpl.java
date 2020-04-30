package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;
import com.skilldistillery.outdistancing.repositories.EventRepository;
import com.skilldistillery.outdistancing.repositories.LocationRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private ActivityRepository actRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LocationRepository locRepo;

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
	public Event createEvent(Event event, int actId, String username) {
		Location newLocation = event.getLocation();
		locRepo.saveAndFlush(newLocation);
		Optional<Activity> optActivity = actRepo.findById(actId);
		User currentUser = userRepo.findByUsername(username);
		if (optActivity.isPresent() && currentUser != null) {
			event.setActivity(optActivity.get());
			event.setCreator(currentUser);
			event.setLocation(newLocation);
			try {
				event = eventRepo.saveAndFlush(event);
				return event;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Event updateEvent(int evtId, Event event, String username) {
		Location updateLocation = event.getLocation();
		locRepo.saveAndFlush(updateLocation);

		Optional<Event> optEvent = eventRepo.findById(evtId);
		User currentUser = userRepo.findByUsername(username);

		if (optEvent.isPresent() && currentUser != null) {
			Event managedEvent = optEvent.get();
			managedEvent.setTitle(event.getTitle());
			managedEvent.setEventTime(event.getEventTime());
			managedEvent.setEventDate(event.getEventDate());
			managedEvent.setShortDescription(event.getShortDescription());
			managedEvent.setDescription(event.getDescription());
			managedEvent.setImageUrl(event.getImageUrl());
			managedEvent.setCreator(currentUser);
			managedEvent.setLocation(updateLocation);
			try {
				managedEvent = eventRepo.saveAndFlush(managedEvent);
				return managedEvent;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Boolean deleteById(int evtId, String username) {
		Optional<Event> optEvent = eventRepo.findById(evtId);
		User currentUser = userRepo.findByUsername(username);
		if (optEvent.isPresent() && currentUser != null) {
			Event deleteEvent = optEvent.get();
			if (deleteEvent != null) {
				eventRepo.deleteById(evtId);
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean changeEventEnabled(int evtId, String username) {
		Optional<Event> optEvent = eventRepo.findById(evtId);
		User currentUser = userRepo.findByUsername(username);
		if (optEvent.isPresent() && currentUser != null) {
			Event updateEvent = optEvent.get();
			updateEvent.setEnabled(!updateEvent.isEnabled());
			eventRepo.saveAndFlush(updateEvent);
			return true;
		} else {
			return false;
		}

	}
}
