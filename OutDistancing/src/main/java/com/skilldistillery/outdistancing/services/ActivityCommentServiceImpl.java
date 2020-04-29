package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.ActivityComment;
import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.ActivityCommentRepository;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class ActivityCommentServiceImpl implements ActivityCommentService {

	@Autowired
	private ActivityCommentRepository comRepo;

	@Autowired
	private ActivityRepository activityRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<ActivityComment> findAll() {
		return comRepo.findAll();
	}

	@Override
	public ActivityComment findById(int id) {
		Optional<ActivityComment> comment = comRepo.findById(id);
		return comment.get();
	}

	@Override
	public ActivityComment addActivityComment(ActivityComment comment, int actId) {
		Optional<Activity> optActivity = activityRepo.findById(actId);
		User newUser = comment.getUser();
		userRepo.saveAndFlush(newUser);
		if (optActivity.isPresent()) {
			comment.setActivity(optActivity.get());
			comment.setUser(newUser);
			try {
				comment = comRepo.saveAndFlush(comment);
				return comment;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public ActivityComment updateActivityComment(ActivityComment comment, int id) {
		Optional<ActivityComment> updatedWorkout = comRepo.findById(id);
		if (updatedWorkout.isPresent()) {
			comment.setId(id);
			comRepo.saveAndFlush(comment);
		}
		return comment;
	}
	
	@Override
	public Boolean changeCommentEnabled(int commentId) {
		Optional<ActivityComment> optComment = comRepo.findById(commentId);
        if (optComment.isPresent()) {
            ActivityComment updateComment = optComment.get();
            updateComment.setEnabled(!updateComment.isEnabled());
			comRepo.save(updateComment);
			return true;
		} else {
			return false;
		}
	}

}
