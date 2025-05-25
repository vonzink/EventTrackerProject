package com.skilldistillery.loantracker.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.skilldistillery.loantracker.entities.Borrower;


public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {

	
	    List<Borrower> findByLastNameContainingIgnoreCase(String lastName);
	  
	    
}
