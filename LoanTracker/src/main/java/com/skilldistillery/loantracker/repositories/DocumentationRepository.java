package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Documentation;

public interface DocumentationRepository extends JpaRepository<Documentation, Integer> {

}
