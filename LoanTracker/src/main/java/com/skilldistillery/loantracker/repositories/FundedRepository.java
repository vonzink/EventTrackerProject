package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Funded;

public interface FundedRepository extends JpaRepository<Funded, Integer> {

}
