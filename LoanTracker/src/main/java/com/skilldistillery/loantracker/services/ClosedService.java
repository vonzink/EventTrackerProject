package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;


import com.skilldistillery.loantracker.entities.Closed;

public interface ClosedService {

	Optional<Closed> findById(int id); 
	Optional<Closed> findByApplicationId(int addId); 
	List<Closed> findAll(); 
	Closed create(Closed closed);

}
