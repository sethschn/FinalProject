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

class LocationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Location location;

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
		location = em.find(Location.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		location= null;
	}

	@Test
	@DisplayName("test Location entity mapping")
	void test() {
		assertNotNull(location);
		assertEquals("7400 E Orchard Rd", location.getStreet());
		assertEquals("Skill Distillery", location.getTitle());
		assertEquals(1, location.getId());
	}
	
	@Test
	@DisplayName("test Location OTM User mapping")
	void test2() {
		assertTrue(location.getUsers().size() > 0);
		assertEquals("m.wood@sd.com", location.getUsers().get(0).getEmail());
		assertEquals("Mary", location.getUsers().get(0).getFirstName());
	}
	
	@Test
	@DisplayName("test Location OTM Event mapping")
	void test3() {
		assertTrue(location.getEvents().size() > 0);
		assertEquals("This is a chance to enjoy the nice weather but also respect distancing", location.getEvents().get(0).getDescription());
		assertEquals("Hiking at a Distance", location.getEvents().get(0).getTitle());
	}
	@Test
	@DisplayName("test Location MTM Activity mapping")
	void test4() {
		assertTrue(location.getActivities().size() > 0);
		assertEquals("You will need a water source and good hiking boots", location.getActivities().get(0).getEquipmentDescription());
		assertEquals(EquipmentLevel.Low, location.getActivities().get(0).getEquipmentLevel());
	}
	
	


}
