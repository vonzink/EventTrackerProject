package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.Funded;

public interface FundedService {

	List<Funded> findAll();
    Funded findById(int id);
    Funded findByApplicationId(int appId);
    Funded create(Funded funded);
}

