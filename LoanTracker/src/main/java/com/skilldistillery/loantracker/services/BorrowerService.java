package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.Borrower;

public interface BorrowerService {

	 List<Borrower> findAll();
	    Borrower findById(int id);
	    Borrower create(Borrower borrower);
	    Borrower update(int id, Borrower borrower);
	    boolean delete(int id);
	    List<Borrower> findByLastName(String lastName);
	}

