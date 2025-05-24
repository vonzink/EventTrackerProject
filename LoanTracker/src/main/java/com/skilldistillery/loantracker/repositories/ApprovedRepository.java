package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Approved;


public interface ApprovedRepository extends JpaRepository<Approved, Integer> {

}
