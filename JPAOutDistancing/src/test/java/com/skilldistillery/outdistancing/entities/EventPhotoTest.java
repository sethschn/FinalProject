package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventPhotoTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventPhoto eventPhoto;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("OutDistancingPU");
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		eventPhoto = em.find(EventPhoto.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventPhoto= null;
	}

	@Test
	@DisplayName("test Event Photo entity mapping")
	void test() {
		assertNotNull(eventPhoto);
		assertEquals("https://www.gohikeit.com/wp-content/uploads/2015/04/hiker-selfie-saddleback-mountain-hiking-trail.jpg", eventPhoto.getImageUrl());
	}
	@Test
	@DisplayName("test Event Photo MTO Event")
	void test2() {
		assertTrue(eventPhoto.getEvent().getEventCmts().size() >0);
		assertEquals("https://www.gohikeit.com/wp-content/uploads/2015/04/hiker-selfie-saddleback-mountain-hiking-trail.jpg", eventPhoto.getImageUrl());
	}

}
