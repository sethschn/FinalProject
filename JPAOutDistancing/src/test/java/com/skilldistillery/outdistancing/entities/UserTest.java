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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+
//	| id | username  | password | email          | first_name | last_name | enabled | location_id | description                                                                       | image_url                                                                                             | role | create_date |
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+
//	|  1 | kissmyaxe | bullseye | p.smith@sd.com | Peggy      | Smith     |       1 |           2 |  Freelance internet buff. Amateur introvert. Writer. Web nerd. Travel aficionado. | https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/GettyImages-1158622531_thumb.jpg | user | 2020-04-28  |
//	+----+-----------+----------+----------------+------------+-----------+---------+-------------+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------+------+-------------+

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("kissmyaxe", user.getUsername());
		assertEquals("bullseye", user.getPassword());
		assertEquals("p.smith@sd.com", user.getEmail());
		assertEquals("Peggy", user.getFirstName());
		assertEquals("Smith", user.getLastName());
		assertTrue(user.isEnabled());
//		assertEquals(2, user.getLocationId());
		assertEquals(" Freelance internet buff. Amateur introvert. Writer. Web nerd. Travel aficionado.", user.getDescription());
		assertEquals("https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/GettyImages-1158622531_thumb.jpg", user.getImageUrl());
		assertEquals("user", user.getRole());
		assertEquals(LocalDate.of(2020, 04, 28), user.getCreateDate());
	}
	
	@Test
	@DisplayName("test User MTO Location mappings")
	void test2() {
		assertNotNull(user.getLocation());
		assertEquals("2710 S Colorado Blvd",user.getLocation().getStreet());
		assertEquals("Denver",user.getLocation().getCity());
		assertEquals("Colorado",user.getLocation().getState());
		assertEquals("80222" ,user.getLocation().getPostalCode());
	}
	
	@Test
	@DisplayName("test User OTM/MTM Event mappings")
	void test4() {
		assertTrue(user.getEvents().size() > 0);
		assertEquals("Hiking at a Distance",user.getEvents().get(0).getTitle());
		assertEquals("This is a chance to enjoy the nice weather but also respect distancing",user.getEvents().get(0).getDescription());
		assertTrue( user.getUserEvents().size() > 0);
	}
	
	@Test
	@DisplayName("test User MTM/OTM Activity mappings")
	void test5() {
		assertTrue(user.getFavoriteActivities().size() > 0);
		assertTrue(user.getActivities().size() > 0);
		assertEquals("Let's go on a wonderful neature walk together",user.getFavoriteActivities().get(0).getDescription());
		assertEquals("Neature Walk Together",user.getActivities().get(0).getShortDescription());
		
	}
	
	@Test
	@DisplayName("test User MTM UserGroup mappings")
	void test3() {
		assertTrue(user.getGroups().size() > 0);
		assertEquals("Hikers United",user.getGroups().get(0).getName());
		assertEquals("For Hardcore Hiking",user.getGroups().get(0).getShortDescription());
	}
	


	
	@Test
	@DisplayName("test User OTM ActivityComment mapping")
	void test6() {
		assertTrue(user.getActivityComments().size() > 0);
		assertEquals("kissmyaxe", user.getActivityComments().get(0).getUser().getUsername());
		assertEquals(LocalDate.of(2020, 04, 28), user.getActivityComments().get(0).getCreateDate());
		assertEquals("dude hiking is my passion", user.getActivityComments().get(0).getContent());
	}
	
	@Test
	@DisplayName("test User OTM EventComment mapping")
	void test7() {
		assertTrue(user.getEventComments().size() > 0);
		assertEquals("yo brosephs what time we meeting up I cant read good", user.getEventComments().get(0).getContent());
		assertEquals(1, user.getEventComments().get(0).getUser().getId());
	}
		

}
