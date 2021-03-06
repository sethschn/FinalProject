package com.skilldistillery.outdistancing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.EventComment;

public interface EventCommentRepository extends JpaRepository<EventComment, Integer> {
	List<EventComment> findByEvent_Id(int id);

}
