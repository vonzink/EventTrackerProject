package com.skilldistillery.loantracker.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.Funded;

public interface FundedRepository extends JpaRepository<Funded, Integer> {

	  Optional<Funded> findByApplication_Id(int appId);

	  
}
