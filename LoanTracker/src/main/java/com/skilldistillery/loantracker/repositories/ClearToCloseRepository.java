package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.ClearToClose;

public interface ClearToCloseRepository extends JpaRepository<ClearToClose, Integer> {

	  List<ClearToClose> findByApplicationId(int applicationId);
	  List<ClearToClose> findByClearToCloseDateIsNotNull();
}
