package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.Declined;

public interface DeclinedService {

	Declined create(Declined declined);
    List<Declined> findAll();
    Declined findById(int id);
    List<Declined> findByApplicationId(int appId);
}
