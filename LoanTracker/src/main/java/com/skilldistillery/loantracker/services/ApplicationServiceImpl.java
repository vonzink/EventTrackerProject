package com.skilldistillery.loantracker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.repositories.ApplicationRepository;


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
	    @Override
	    public boolean disable(int id) {
	        Optional<Application> opt = appRepo.findById(id);
	        if (opt.isPresent()) {
	            Application app = opt.get();
	            app.setEnabled(false);
	            appRepo.save(app);
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public boolean enable(int id) {
	        Optional<Application> opt = appRepo.findById(id);
	        if (opt.isPresent()) {
	            Application app = opt.get();
	            app.setEnabled(true);
	            appRepo.save(app);
	            return true;
	        }
	        return false;
	    }
	    public List<Application> findByPropertyAddress(String address) {
	        return appRepo.findByPropertyAddressContainingIgnoreCase(address);
	    }
	    public List<Application> findByStatus(String status) {
	        return appRepo.findByStatus(status);
	    }
		public List<Application> findBySubmittedDateRange(LocalDate start, LocalDate end) {
			 return appRepo.findBySubmittedDateBetween(start, end);
		}
		public List<Application> findByBorrowerName(String name) {
			return appRepo.findByBorrowerFirstNameContainingIgnoreCase(name);
		}
		public List<Application> findByBorrowerLastName(String name) {
			 return appRepo.findByBorrowerLastNameContainingIgnoreCase(name); 
		}
		@Override
		public List<Application> findAllEnabled() {
			return appRepo.findByEnableTrue();
		}
}
