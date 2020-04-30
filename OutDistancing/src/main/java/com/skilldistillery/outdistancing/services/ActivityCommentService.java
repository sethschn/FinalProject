package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.ActivityComment;

public interface ActivityCommentService {

	List<ActivityComment> findAll();
	
	List<ActivityComment> findByActivityId(int id);

	ActivityComment findById(int id);

	ActivityComment addActivityComment(ActivityComment comment, int actId, String username);

	ActivityComment updateActivityComment(ActivityComment comment, int id, String username);

	Boolean changeCommentEnabled(int commentId, String username);

}
