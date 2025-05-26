package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.loantracker.entities.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	  List<Status> findByApplicationId(int appId);
	  List<Status> findByStatusContainingIgnoreCase(String status);
	  List<Status> findByNotesContainingIgnoreCase(String notes);
	  List<Status> findByChangedByUsernameContainingIgnoreCase(String username);
}
