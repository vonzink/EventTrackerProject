package com.skilldistillery.loantracker.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Application;


public interface ApplicationRepository extends JpaRepository<Application, Integer> {

		List<Application> findByBorrower_FirstNameContainingIgnoreCase(String firstName);
		List<Application> findByBorrower_LastNameContainingIgnoreCase(String lastName);
	    List<Application> findByPropertyAddressContainingIgnoreCase(String address);
	    List<Application> findByStatus(String status);
	    List<Application> findBySubmittedDateBetween(LocalDate start, LocalDate end);
	    List<Application> findByBorrowerId(int borrowerId);
	    List<Application> findByLoanNumber(Integer borrowerId);
	    List<Application> findByEnableTrue();
	  
}
