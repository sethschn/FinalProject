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

class ActivityCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ActivityComment activityCmt;

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
		activityCmt = em.find(ActivityComment.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activityCmt= null;
	}

	@Test
	@DisplayName("test Activity Commententity mapping")
	void test() {
		assertNotNull(activityCmt);
		assertEquals("highly doubtful, prove it n00b", activityCmt.getContent());
		assertEquals(2, activityCmt.getId());
	}
	
	

}
