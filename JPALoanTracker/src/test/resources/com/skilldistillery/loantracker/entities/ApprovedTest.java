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

class ApprovedTest {

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
	    void test_entity_fields() {
	        Approved approval = em.find(Approved.class, 1);
	        assertNotNull(approval);
	        assertNotNull(approval.getApprovalDate());
	        assertEquals(30, approval.getTermYears());
	    }
    }
