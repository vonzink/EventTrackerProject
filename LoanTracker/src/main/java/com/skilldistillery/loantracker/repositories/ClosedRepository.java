package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Closed;

public interface ClosedRepository extends JpaRepository<Closed, Integer> {

}
