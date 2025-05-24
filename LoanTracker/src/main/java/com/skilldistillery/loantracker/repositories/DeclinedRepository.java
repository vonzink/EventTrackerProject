package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Declined;


public interface DeclinedRepository extends JpaRepository<Declined, Integer> {

}
