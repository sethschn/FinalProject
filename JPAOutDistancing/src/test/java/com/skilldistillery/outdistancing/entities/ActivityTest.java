package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.*;

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

class ActivityTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Activity activity;

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
		activity = em.find(Activity.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activity= null;
	}

	@Test
	@DisplayName("test Activity entity mapping")
	void test() {
		assertNotNull(activity);
		assertEquals("Hiking", activity.getTitle());
		assertEquals(1, activity.getId());
	}
	
	@Test
	@DisplayName("test ENUM mappings")
	void test2() {
	assertEquals(EquipmentLevel.Low, activity.getEquipmentLevel());
	}
	
	@Test
	@DisplayName("test relationships to resources")
	void test3() {
		assertNotNull(activity.getResources());
		assertTrue(activity.getResources().size()>0);
		
	}
	
	@Test
	@DisplayName("test Activity MTM Location mappings")
	void test4() {
		assertTrue(activity.getLocations().size() > 0);
		assertEquals("https://skilldistillery.com/", activity.getLocations().get(0).getLocationUrl());
		assertEquals("Greenwood Village", activity.getLocations().get(0).getCity());
		assertEquals("Colorado", activity.getLocations().get(0).getState());
	}

}
