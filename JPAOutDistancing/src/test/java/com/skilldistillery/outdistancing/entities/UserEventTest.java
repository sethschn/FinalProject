package com.skilldistillery.outdistancing.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserEventTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserEvent userEvent;

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
		//userEvent = em.find(UserEvent.class, 1);
		userEvent = em.find(UserEvent.class, new UserEventId(1,1));
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userEvent = null;
	}
	
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+
//	| id | username  | password | email          | first_name | last_name | enabled | location_id | description                                                                       | image_url                                                                                             | role | create_date |
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+
//	|  1 | kissmyaxe | bullseye | p.smith@sd.com | Peggy      | Smith     |       1 |           2 |  Freelance internet buff. Amateur introvert. Writer. Web nerd. Travel aficionado. | https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/GettyImages-1158622531_thumb.jpg | user | 2020-04-28  |
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+

	@Test
	void test() {
		assertNotNull(userEvent);
		assertEquals(5, userEvent.getRating());
		assertEquals("once I figured out what time the event was I had a great time hiking with the brotatoes", userEvent.getRatingComment());

	}
	
	@Test
	@DisplayName("test user_event MTO User mappings")
	void test2() {
		assertNotNull(userEvent.getUser());
		assertEquals("Peggy", userEvent.getUser().getFirstName());

	}
	
	
	@Test
	@DisplayName("test user_event MTO Event mappings")
	void test4() {
		assertNotNull(userEvent.getEvent());
		assertEquals("Hiking at a Distance", userEvent.getEvent().getTitle());
	}
		

}
