package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	
}
