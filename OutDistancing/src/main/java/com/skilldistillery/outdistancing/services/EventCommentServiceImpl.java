package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.EventComment;
import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;
import com.skilldistillery.outdistancing.repositories.EventCommentRepository;
import com.skilldistillery.outdistancing.repositories.EventRepository;
import com.skilldistillery.outdistancing.repositories.LocationRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class EventCommentServiceImpl implements EventCommentService {

	@Autowired
	private EventCommentRepository eventCmtRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private ActivityRepository actRepo;
	@Autowired
	private LocationRepository locRepo;
	
	@Override
	public List<EventComment> findAll() {
		return eventCmtRepo.findAll();
	}

	@Override
	public EventComment findById(int eventCommentId) {
		Optional<EventComment> optEventCmt = eventCmtRepo.findById(eventCommentId);
		EventComment eventCmt = null;
		if (optEventCmt.isPresent()) {
			eventCmt = optEventCmt.get();
		} else {
			return null;
		}
		return eventCmt;
	}

	@Override
	public EventComment createEventComment(EventComment eventCmt, int evtId, String username) {
		Optional<Event> optEvent = eventRepo.findById(evtId);
		User currentUser = userRepo.findByUsername(username);
		if(optEvent.isPresent() && currentUser != null) {
			eventCmt.setEvent(optEvent.get());
			eventCmt.setUser(currentUser);
			try {
				eventCmt = eventCmtRepo.saveAndFlush(eventCmt);
				return eventCmt;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public EventComment updateEventComment(int evtId, EventComment eventCmt, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(int eventCommentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changeEventCommentEnabled(int eventCommentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
