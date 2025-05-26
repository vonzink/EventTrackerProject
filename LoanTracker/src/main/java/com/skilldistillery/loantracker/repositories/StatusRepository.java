package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.loantracker.entities.Status;


public interface StatusRepository extends JpaRepository<Status, Integer> {
	List<Status> findByApplicationIdOrderByChangedAtDesc(int applicationId);
}
