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

class CategoryTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category category;

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
		category = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		category = null;
	}
	
//	+----+---------------------+-----------------------+---------+---------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------+
//	| id | type                | short_description     | enabled | description                                 | image_url                                                                                                                                        |
//	+----+---------------------+-----------------------+---------+---------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------+
//	|  1 | Mountain Activities | where dat mountain at |       1 | these activities require a friggin mountain | https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Ftrevornace%2Ffiles%2F2015%2F11%2Fmt-everest-sunset-1200x675.jpg |
//	+----+---------------------+-----------------------+---------+---------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------+

	@Test
	void test() {
		assertNotNull(category);
		assertEquals("Mountain Activities",category.getType());
		assertEquals("where dat mountain at",category.getShortDescription());
		assertTrue(category.isEnabled());
		assertEquals("these activities require a friggin mountain",category.getDescription());
		assertEquals("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Ftrevornace%2Ffiles%2F2015%2F11%2Fmt-everest-sunset-1200x675.jpg",category.getImageUrl());
	}

}
