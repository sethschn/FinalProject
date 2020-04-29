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
	
	@Test
	@DisplayName("test Activity OTM Event mappings")
	void test5() {
		assertTrue(activity.getEvents().size() > 0);
		assertEquals("Hiking at a Distance", activity.getEvents().get(0).getTitle());
		assertEquals("Hike in the nice weather", activity.getEvents().get(0).getShortDescription());
	}
	
	@Test
	@DisplayName("test Activity MTM User (favorite lists) mappings")
//	select * from user join activity where activity.id = 1;
	void test6() {
		assertTrue(activity.getUsers().size() > 0);
		assertEquals("kissmyaxe", activity.getUsers().get(0).getUsername());
		assertEquals("Peggy", activity.getUsers().get(0).getFirstName());
	}
	
//	@Test
//	@DisplayName("test Activity ORM Activity Comment mappings")
//	select * from activity_comment join activity where activity.id = 1;
	//	void test7() {
//		assertTrue(activity.getActivityComments().size() > 0);
//		assertEquals("dude hiking is my passion", activity.getActivityComments().get(0).getContent());
//	}
	
	@Test
	@DisplayName("test Activity MTM Activity Category mappings")
//	select * from user category activity where activity.id = 1;
	void test8() {
		assertTrue(activity.getCategories().size() > 0);
		assertEquals("Mountain Activities", activity.getCategories().get(0).getType());
		assertEquals("where dat mountain at", activity.getCategories().get(0).getShortDescription());
	}
	
	@Test
	@DisplayName("test Activity MTM User (favorite lists) mappings")
//	 select * from user join activity where activity.creator_id = 1 and user.id = 1;
	void test9() {
		assertNotNull(activity.getUser());
		assertEquals("kissmyaxe", activity.getUser().getUsername());
		assertEquals("Peggy", activity.getUser().getFirstName());
	}
	


}
