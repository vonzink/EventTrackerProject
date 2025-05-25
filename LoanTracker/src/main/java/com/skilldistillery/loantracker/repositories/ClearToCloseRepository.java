package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.ClearToClose;

public interface ClearToCloseRepository extends JpaRepository<ClearToClose, Integer> {

}
