package com.skilldistillery.loantracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Declined;
import com.skilldistillery.loantracker.repositories.DeclinedRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeclinedServiceImpl implements DeclinedService {

	@Autowired
	private DeclinedRepository declinedRepo;

	@Override
	public List<Declined> findAll() {
		return declinedRepo.findAll();
	}

	@Override
	public Declined findById(int id) {
		return declinedRepo.findById(id).orElse(null);

	}

	@Override
	public Declined create(Declined declined) {
		return declinedRepo.save(declined);
	}

	@Override
	public List<Declined> findByApplicationId(int appId) {
		return declinedRepo.findByApplicationId(appId);
	}
}
