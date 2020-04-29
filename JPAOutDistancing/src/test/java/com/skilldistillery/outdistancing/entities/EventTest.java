package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Event event;

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
		event = em.find(Event.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		event = null;
	}

	@Test
	@DisplayName("test Event entity mapping")
	void test() {
		assertNotNull(event);
		assertEquals("Hiking at a Distance", event.getTitle());
		assertEquals("Hike in the nice weather", event.getShortDescription());

	}

	@Test
	@DisplayName("test Event OTM EventComment mapping")
	void test2() {
		assertTrue(event.getEventCmts().size() > 0);
		assertEquals("yo brosephs what time we meeting up I cant read good", event.getEventCmts().get(0).getContent());

	}

	@Test
	@DisplayName("test Event MTM User mapping")
	void test3() {
		assertTrue(event.getUsers().size() > 0);
		assertEquals("Peggy", event.getUsers().get(0).getFirstName());
		assertEquals("Smith", event.getUsers().get(0).getLastName());
		
	}

	@Test
	@DisplayName("test Event MTO Activity mapping")
	void test4() {
		assertTrue(event.getActivity().getResources().size() > 0);
	}

	@Test
	@DisplayName("test Event MTO Location mapping")
	void test5() {
		assertNotNull(event.getLocation());
		assertEquals("Colorado", event.getLocation().getState());
		assertEquals("Greenwood Village", event.getLocation().getCity());
	}
	
	@Test
	@DisplayName("test Event OTM EventPhoto Mapping")
	void test6() {
		assertTrue(event.getEventPhotos().size() >0);
		assertEquals("https://www.gohikeit.com/wp-content/uploads/2015/04/hiker-selfie-saddleback-mountain-hiking-trail.jpg", 
				event.getEventPhotos().get(0).getImageUrl());
	}

}
