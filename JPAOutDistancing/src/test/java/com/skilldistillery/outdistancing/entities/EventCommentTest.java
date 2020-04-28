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

class EventCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventComment eventCmt;

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
		eventCmt = em.find(EventComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		eventCmt= null;
	}

	@Test
	@DisplayName("test Event Comment entity mapping")
	void test() {
		assertNotNull(eventCmt);
		assertEquals("yo brosephs what time we meeting up I cant read good", eventCmt.getContent());
		assertEquals(LocalDateTime.of( 2020,04,28, 00,00,00), eventCmt.getCreateDate());
		assertEquals(1, eventCmt.getEventId());
		
	}

}
