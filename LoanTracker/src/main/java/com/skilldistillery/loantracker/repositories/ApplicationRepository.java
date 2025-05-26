package com.skilldistillery.loantracker.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

		List<Application> findByBorrowerLastNameContainingIgnoreCase(String name);
		List<Application> findByBorrowerFirstNameContainingIgnoreCase(String name);
	    List<Application> findByPropertyAddressContainingIgnoreCase(String address);
	    List<Application> findByStatus(String status);
	    List<Application> findBySubmittedDateBetween(LocalDate start, LocalDate end);
	    List<Application> findByBorrowerId(int borrowerId);
	  
	    
}
