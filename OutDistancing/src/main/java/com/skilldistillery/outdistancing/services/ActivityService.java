package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Activity;

public interface ActivityService {
	List <Activity> findAll();

	Activity findById(int id);

	Activity addActivity(String username, Activity activity);

	Activity updateActivity(String username, Activity activity, int id);

	Boolean changeActivityEnabled(String username, String title);

}
