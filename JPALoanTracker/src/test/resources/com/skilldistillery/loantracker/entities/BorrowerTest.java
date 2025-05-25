package com.skilldistillery.loantracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class BorrowerTest {

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
	    void test_borrower_basic_fields() {
	        Borrower borrower = em.find(Borrower.class, 1);
	        assertNotNull(borrower);
	        assertEquals("Diane", borrower.getFirstName());
	        assertEquals("Suelter", borrower.getLastName());
	        assertEquals("ds@gmail.com", borrower.getEmail());
	        assertEquals("3033003030", borrower.getPhone());
	        assertTrue(borrower.getCreatedAt() instanceof LocalDateTime);
	    }

	    @Test
	    void test_borrower_applications_relationship() {
	        Borrower borrower = em.find(Borrower.class, 1);
	        assertNotNull(borrower);
	        List<Application> apps = borrower.getApplications();
	        assertNotNull(apps);
	        assertTrue(apps.size() > 0);
	    }
    }
