package com.skilldistillery.loantracker.services;

import java.util.List;
import com.skilldistillery.loantracker.entities.Borrower;

public interface BorrowerService {

	List<Borrower> findAll();
	Borrower findById(int id);
	List<Borrower> findByFirstNameContains(String keyword);
	List<Borrower> findByPhoneContains(String keyword);
	Borrower create(Borrower borrower);
	Borrower update(int id, Borrower borrower);
	boolean delete(int id);
	}

