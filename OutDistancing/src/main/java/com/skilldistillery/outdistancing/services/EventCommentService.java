package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.EventComment;

public interface EventCommentService {
	
	List<EventComment> findAll();

	EventComment findById(int eventCommentId);

	EventComment createEventComment(EventComment eventCmt, int evtId, String username);

	EventComment updateEventComment(int evtId , EventComment eventCmt, String username);

	Boolean deleteById(int eventCommentId);
	
	Boolean changeEventCommentEnabled(int eventCommentId);

}
