package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.ActivityComment;
import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.EventComment;
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
	public ActivityComment addActivityComment(ActivityComment comment, int actId, String username) {
		Optional<Activity> optActivity = activityRepo.findById(actId);
		User user = userRepo.findByUsername(username);
		if (optActivity.isPresent()) {
			comment.setActivity(optActivity.get());
			comment.setUser(user);
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
	public ActivityComment updateActivityComment(ActivityComment comment, int id, String username) {
		Optional<ActivityComment> optComment = comRepo.findById(id);
		User currentUser = userRepo.findByUsername(username);
		if (optComment.isPresent() && currentUser != null) {
			ActivityComment managedtComment = optComment.get();
			managedtComment.setContent(comment.getContent());
			managedtComment.setParentComment(comment.getParentComment());
			managedtComment.setUser(currentUser);

			try {
				managedtComment = comRepo.saveAndFlush(managedtComment);
				return managedtComment;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	
	@Override
	public Boolean changeCommentEnabled(int commentId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<ActivityComment> optComment = comRepo.findById(commentId);
        if (optComment.isPresent() && user != null) {
            ActivityComment updateComment = optComment.get();
            updateComment.setEnabled(!updateComment.isEnabled());
			comRepo.save(updateComment);
			return true;
		} else {
			return false;
		}
	}

}
