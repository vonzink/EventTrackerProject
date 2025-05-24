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
    
    List<Application> findByBorrowerName(String name);
    List<Application> findByPropertyAddress(String address);
    List<Application> findByStatus(String status);
    List<Application> findBySubmissionDateRange(LocalDate start, LocalDate end);
}

