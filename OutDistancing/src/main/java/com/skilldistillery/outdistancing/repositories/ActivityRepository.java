package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

}
