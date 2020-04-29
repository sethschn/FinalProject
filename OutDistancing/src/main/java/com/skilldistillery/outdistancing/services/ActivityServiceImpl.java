package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

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
	public Activity addActivity(Activity activity) {
		try {
			activity = activityRepo.saveAndFlush(activity);
			return activity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Activity updateActivity(Activity activity, int id) {
		Optional<Activity> updatedActivity = activityRepo.findById(id);
		if (updatedActivity.isPresent()) {
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

//	@Override
//	public Boolean changeActivityEnabled(int id) {
//		Activity activity = null;
//		activity = this.findById(id);
//		if (activity != null) {
//			if (activity.getEnabled() == true) {
//				activity.setEnabled(false);
//				activityRepo.saveAndFlush(activity);
//			}
//			if (activity.getEnabled() == false) {
//				activity.setEnabled(true);
//				activityRepo.saveAndFlush(activity);
//			}
//		}
//		return activity.getEnabled();
//	}
	
	@Override
	public Boolean changeActivityEnabled(String title) {
		Activity activity = null;
		activity = activityRepo.findByTitle(title);
		if (activity != null) {
			activity.setEnabled(!activity.getEnabled());
			activityRepo.save(activity);
			return true;
		} else {
			return false;
		}
	}
	
	
}
