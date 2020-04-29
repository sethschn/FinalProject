package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Activity;

public interface ActivityService {
	List <Activity> findAll();

	Activity findById(int id);

	Activity addActivity(Activity activity);

	Activity updateActivity(Activity activity, int id);

	Boolean changeActivityEnabled(String title);

}
