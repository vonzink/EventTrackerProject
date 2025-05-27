package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.loantracker.entities.Closed;
import com.skilldistillery.loantracker.repositories.ClosedRepository;

@Service
public class ClosedServiceImpl implements ClosedService {

	@Autowired
	public ClosedRepository closedRepo;

	@Override
	public Optional<Closed> findById(int id) {
		Optional<Closed> opt = closedRepo.findById(id);
		return opt.isPresent() ? Optional.of(opt.get()) : null;
	}
	@Override
	public Optional<Closed> findByApplicationId(int addId) {
		Optional<Closed> opt = closedRepo.findById(addId);
		return opt.isPresent() ? Optional.of(opt.get()) : null;
	}
	@Override
	public List<Closed> findAll() {
		return closedRepo.findAll();
	}
	@Override
	public Closed create(Closed closed) {
		return closedRepo.save(closed);
	}
}
