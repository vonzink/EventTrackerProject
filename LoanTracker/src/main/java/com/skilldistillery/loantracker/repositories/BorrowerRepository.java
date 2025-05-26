package com.skilldistillery.loantracker.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.skilldistillery.loantracker.entities.Borrower;


public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {

	  List<Borrower> findByFirstNameContainingIgnoreCase(String firstName);
	  List<Borrower> findByLastNameContainingIgnoreCase(String lastName);
	  List<Borrower> findByPhoneContaining(String phone);
	  Borrower findByEmail(String email);
	  
	    
}
