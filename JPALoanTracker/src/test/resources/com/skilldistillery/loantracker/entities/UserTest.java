package com.skilldistillery.loantracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPALoanTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
    void test_find_user_by_id() {
        User user = em.find(User.class, 1); 
		assertNotNull(user);
		assertEquals("Zach", user.getFirstName());
		assertEquals("Zink", user.getLastName());
		assertEquals("vonzink", user.getUsername());
		assertEquals("LO", user.getRole());
		assertEquals("1234", user.getPassword()); 
		assertEquals("vonzink@gmail.com", user.getEmail());
		assertTrue(user.getCreatedAt() instanceof LocalDateTime);
		}
    }
