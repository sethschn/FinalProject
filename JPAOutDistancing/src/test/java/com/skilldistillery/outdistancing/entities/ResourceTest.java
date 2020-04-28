package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResourceTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Resource resource;

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
		resource = em.find(Resource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		resource= null;
	}

	@Test
	void test() {
		assertNotNull(resource);
		assertEquals("Hiking for Beginnners", resource.getName());
		assertEquals("Unlike walking on a treadmill or paved path, hiking involves more, sometimes unpredictable, variables. Of course, these variables are part of what makes it so enjoyable! Use the following hiking tips to make your first treks successful", resource.getDescription());
		assertEquals("https://blog.liftopia.com/10-essential-hiking-tips-beginner-hike/", resource.getLink());
		assertEquals("https://10c9rz12yp4b46gr6a2oxgub-wpengine.netdna-ssl.com/wp-content/uploads/DSC_0879.jpg", resource.getImageUrl());
	}

}
