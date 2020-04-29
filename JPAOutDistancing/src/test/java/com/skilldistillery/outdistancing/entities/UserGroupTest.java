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

class UserGroupTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserGroup userGroup;

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
		userGroup = em.find(UserGroup.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userGroup= null;
	}

	@Test
	@DisplayName("test Activity entity mapping")
	void test() {
		assertNotNull(userGroup);
		assertEquals("Hikers United", userGroup.getName());
		assertEquals("For Hardcore Hiking", userGroup.getShortDescription());
		assertEquals(1, userGroup.getId());
	}
	
	@Test
	@DisplayName("test UserGroup MTM User mapping")
	void test2() {
		assertNotNull(userGroup.getUsers().size() > 0);
		assertEquals("p.smith@sd.com", userGroup.getUsers().get(0).getEmail());
		assertEquals("Peggy", userGroup.getUsers().get(0).getFirstName());
	}


}
