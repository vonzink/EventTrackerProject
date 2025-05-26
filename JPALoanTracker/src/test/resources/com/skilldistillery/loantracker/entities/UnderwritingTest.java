package com.skilldistillery.loantracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class UnderwritingTest {

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
	void test_underwriting_entity_mapping() {
		Underwriting uw = em.find(Underwriting.class, 1);
		assertNotNull(uw);
		assertEquals(1, uw.getApplication().getId());
		assertEquals("seth", uw.getUnderwriterName());
		assertNotNull(uw.getFindings());
		assertNotNull(uw.getReviewedDate());
		assertTrue(uw.getReviewedDate().isBefore(LocalDate.now().plusDays(1)));
		assertEquals("approved", uw.getDecision().toLowerCase());
		assertNotNull(uw.getUnderwriterName());
	}
}
