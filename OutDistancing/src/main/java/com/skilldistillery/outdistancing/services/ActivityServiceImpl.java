package com.skilldistillery.outdistancing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public List<Activity> findAll() {
		return activityRepo.findAll();
	}

}
