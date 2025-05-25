package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Approved;
import com.skilldistillery.loantracker.entities.Borrower;


public interface ApprovedRepository extends JpaRepository<Borrower, Integer> {

	
}
