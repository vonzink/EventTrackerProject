package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.ClearToClose;

public interface ClearToCloseService {

	List<ClearToClose> findAll();
    List<ClearToClose> findByCtcDateIsNotNull();
    
}
