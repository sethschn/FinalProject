package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Activity> findAll() {
		return activityRepo.findAll();
	}

	@Override
	public Activity findById(int id) {
		Optional<Activity> activity = activityRepo.findById(id);
		return activity.get();
	}

	@Override
	public Activity addActivity(String username, Activity activity) {
		User user = userRepo.findByUsername(username);
		 if (user != null) {
	            activity.setUser(user);
	            activityRepo.saveAndFlush(activity);
	        } else {
	        	activity = null;
	        }
	        return activity;
	    }

	@Override
	public Activity updateActivity(String username, Activity activity, int id) {
		Optional<Activity> updatedActivity = activityRepo.findById(id);
		User currentUser = userRepo.findByUsername(username);
		if (updatedActivity.isPresent() && currentUser != null) {
			Activity managedActivity = updatedActivity.get();
			managedActivity.setTitle(activity.getTitle());
			managedActivity.setShortDescription(activity.getShortDescription());
			managedActivity.setDescription(activity.getDescription());
			managedActivity.setEnabled(activity.getEnabled());
			managedActivity.setImageUrl(activity.getImageUrl());
			managedActivity.setEquipmentLevel(activity.getEquipmentLevel());
			managedActivity.setEquipmentDescription(activity.getDescription());
			return activityRepo.saveAndFlush(managedActivity);
		}
		return null;
	}


	@Override
	public Boolean changeActivityEnabled(String username, String title) {
		User user = userRepo.findByUsername(username);
		Activity activity = null;
		activity = activityRepo.findByTitle(title);
		if (activity != null && user != null) {
			activity.setEnabled(!activity.getEnabled());
			activityRepo.save(activity);
			return true;
		} else {
			return false;
		}
	}

}
