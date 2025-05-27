package com.skilldistillery.loantracker.services;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.loantracker.entities.Application;


public interface ApplicationService {

	List<Application> findAll();
    Application findById(int id);
    Application create(Application application);
    Application update(int id, Application application);
    boolean delete(int id);
    boolean disable(int id);
    boolean enable(int id);
    List<Application> findAllEnabled();
    
    List<Application> findByBorrowerName(String name);
    List<Application> findByPropertyAddress(String address);
    List<Application> findByStatus(String status);
    List<Application> findBySubmittedDateRange(LocalDate start, LocalDate end);
	List<Application> findByBorrowerLastName(String name);
}

