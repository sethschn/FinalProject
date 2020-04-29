package com.skilldistillery.outdistancing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Activity;
import com.skilldistillery.outdistancing.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	List<Activity> findActivities();

}
