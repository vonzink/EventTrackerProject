package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.Documentation;

public interface DocumentationService {

	 	List<Documentation> findAll();
	    Documentation findById(int id);
	    Documentation create(Documentation documentation);
	    Documentation update(int id, Documentation documentation);
	    boolean delete(int id);
	}

