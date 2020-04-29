package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

}
