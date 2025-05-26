package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Closed;

public interface ClosedRepository extends JpaRepository<Closed, Integer> {

	  List<Closed> findByApplicationId(int appId);

}
