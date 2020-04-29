package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
