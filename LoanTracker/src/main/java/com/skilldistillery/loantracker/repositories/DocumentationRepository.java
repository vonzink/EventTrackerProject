package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Documentation;

public interface DocumentationRepository extends JpaRepository<Documentation, Integer> {

		List<Documentation> findByApplicationId(int appId);
		List<Documentation> findByDocumentTypeContainingIgnoreCase(String documentType);
		List<Documentation> findByRequestedByContainingIgnoreCase(String requestedBy);
	}

