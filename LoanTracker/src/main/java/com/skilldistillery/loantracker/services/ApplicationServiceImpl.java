package com.skilldistillery.loantracker.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.repositories.ApplicationRepository;
import com.skilldistillery.loantracker.repositories.UserRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	 @Autowired
	    private ApplicationRepository appRepo;

	 public List<Application> findAll() {
	        return appRepo.findAll();
	    }
	    public Application findById(int id) {
	        return appRepo.findById(id).orElse(null);
	    }
	    public Application create(Application app) {
	        return appRepo.save(app);
	    }
	    public Application update(int id, Application app) {
	        if (!appRepo.existsById(id)) return null;
	        app.setId(id);
	        return appRepo.save(app);
	    }
	    public boolean delete(int id) {
	        if (appRepo.existsById(id)) {
	            appRepo.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	    public List<Application> findByBorrowerName(String name) {
	        return appRepo.findByBorrowerNameContainingIgnoreCase(name);
	    }
	    public List<Application> findByPropertyAddress(String address) {
	        return appRepo.findByPropertyAddressContainingIgnoreCase(address);
	    }
	    public List<Application> findByStatus(String status) {
	        return appRepo.findByStatus(status);
	    }
		public List<Application> findBySubmissionDateRange(LocalDate start, LocalDate end) {
			 return appRepo.findBySubmissionDateBetween(start, end);
		}
}
