package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.loantracker.entities.Approved;

public interface ApprovedService {

	 	Optional<Approved> findById(int id);
	    List<Approved> findAll();
	    Optional<Approved> findByApplicationId(int appId);
	    Approved create(Approved approved);
	    Approved update(int id, Approved approved);
	    boolean delete(int id);
}
