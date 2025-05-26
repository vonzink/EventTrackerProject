package com.skilldistillery.loantracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.ClearToClose;
import com.skilldistillery.loantracker.repositories.ClearToCloseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClearToCloseServiceImpl implements ClearToCloseService {

	 @Autowired
	    private ClearToCloseRepository repo;

	    @Override
	    public List<ClearToClose> findAll() {
	        return repo.findAll();
	    }

	    @Override
	    public List<ClearToClose> findByCtcDateIsNotNull() {
	        return repo.findByCtcDateIsNotNull();
	    }
	    
}
