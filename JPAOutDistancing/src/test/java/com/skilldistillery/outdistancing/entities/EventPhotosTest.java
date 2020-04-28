package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

class EventPhotosTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventPhotos eventPhoto;

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
		eventPhoto = em.find(EventPhotos.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventPhoto= null;
	}

	@Test
	@DisplayName("test Event Comment entity mapping")
	void test() {
		assertNotNull(eventPhoto);
		assertEquals(1, eventPhoto.getEventId());
		assertEquals("https://www.gohikeit.com/wp-content/uploads/2015/04/hiker-selfie-saddleback-mountain-hiking-trail.jpg", eventPhoto.getImageUrl());
	
		
	}

}
