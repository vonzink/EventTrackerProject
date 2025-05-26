package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.loantracker.entities.Underwriting;


public interface UnderwritingService {

	 	Underwriting findById(int id);
	    List<Underwriting> findAll();
	    Underwriting create(Underwriting underwriter);
	    Underwriting update(int id, Underwriting underwriter);
	    boolean delete(int id);
	    Optional<Underwriting> findByApplicationId(int appId);
	    Optional<Underwriting> findByUnderwriterName(String name);
	    List<Underwriting> findByDecision(String decision);
	    List<Underwriting> findByFindings(String keyword);
}

