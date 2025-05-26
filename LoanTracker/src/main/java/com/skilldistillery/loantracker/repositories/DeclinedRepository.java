package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Declined;


public interface DeclinedRepository extends JpaRepository<Declined, Integer> {

	
	  List<Declined> findByApplicationId(int appId);
	  List<Declined> findByDeclinedByContainingIgnoreCase(String declinedBy);
	  List<Declined> findByReasonContainingIgnoreCase(String reason);
	  
}
