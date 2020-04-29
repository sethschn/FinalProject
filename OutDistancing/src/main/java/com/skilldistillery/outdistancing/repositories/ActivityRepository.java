package com.skilldistillery.outdistancing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	Activity findByTitle(String title);
	List <Activity> findAllByUserUsername(String username);
}
