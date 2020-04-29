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
		activityCmt = em.find(ActivityComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		activityCmt= null;
	}

//	+----+---------------------------+---------------------+---------+---------+-------------+-------------+
//	| id | content                   | create_date         | enabled | user_id | activity_id | in_reply_id |
//	+----+---------------------------+---------------------+---------+---------+-------------+-------------+
//	|  1 | dude hiking is my passion | 2020-04-28 00:00:00 |       1 |       1 |           1 |        NULL |
//	+----+---------------------------+---------------------+---------+---------+-------------+-------------+
	@Test
	@DisplayName("test Activity Commententity mapping")
	void test1() {
		assertNotNull(activityCmt);
		assertEquals("dude hiking is my passion", activityCmt.getContent());
		assertEquals(1, activityCmt.getId());
	}
	
	@Test
	@DisplayName("test Activity Comment MTM User mapping")
//	 select * from user join activity_comment where activity_comment.user_id = user.id;
	void test2() {
		assertNotNull(activityCmt);
		assertEquals("kissmyaxe", activityCmt.getUser().getUsername());
		assertEquals("Peggy", activityCmt.getUser().getFirstName());
	}
	
	@Test
	@DisplayName("test Activity Comment MTM Activity mapping")
//	 select * from activity join activity_comment where activity_comment.activity_id = activity.id;
	void test3() {
		assertNotNull(activityCmt);
		assertEquals("Hiking", activityCmt.getActivity().getTitle());
		assertEquals("Neature Walk Together", activityCmt.getActivity().getShortDescription());
	}
	
	@Test
	@DisplayName("test Activity Comment OTO Activity Comment mapping")
//	 select * from activity_comment a1 inner join activity_comment a2 where a1.in_reply_id = a2.id;
	void test4() {
		assertNotNull(activityCmt);
		assertEquals(2, activityCmt.getParentComment().getId());
		assertEquals("highly doubtful, prove it n00b", activityCmt.getParentComment().getContent());
		assertEquals("shaquilleoatmeal", activityCmt.getParentComment().getUser().getFirstName());
	}
	

}
