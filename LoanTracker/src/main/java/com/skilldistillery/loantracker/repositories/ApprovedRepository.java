package com.skilldistillery.loantracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Approved;


public interface ApprovedRepository extends JpaRepository<Approved, Integer> {

	  	Optional<Approved> findByApplicationId(int applicationId);
	    List<Approved> findByApprovedByContainingIgnoreCase(String name);
	    List<Approved> findByApprovalNotesContainingIgnoreCase(String keyword);
}
