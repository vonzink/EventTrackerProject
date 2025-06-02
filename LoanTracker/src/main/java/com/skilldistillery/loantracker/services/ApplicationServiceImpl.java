package com.skilldistillery.loantracker.services;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.entities.Borrower;
import com.skilldistillery.loantracker.repositories.ApplicationRepository;


@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository appRepo;
	@Autowired

	public List<Application> findAll() {
		return appRepo.findAll();
	}

	public Application findById(int id) {
		return appRepo.findById(id).orElse(null);
	}

	public Application create(Application app) {
		return appRepo.save(app);
	}

	@Override
	public Application update(int id, Application app) {
	    Application existing = appRepo.findById(id).orElse(null);
	    if (existing == null) return null;
	    if (app.getLoanNumber() != null && !app.getLoanNumber().toString().isBlank()) {
	        existing.setLoanNumber(app.getLoanNumber());
	    }
	    if (app.getPropertyAddress() != null) existing.setPropertyAddress(app.getPropertyAddress());
	    if (app.getLoanAmount()      != null) existing.setLoanAmount(app.getLoanAmount());
	    if (app.getLoanType()        != null) existing.setLoanType(app.getLoanType());
	    if (app.getPurpose()         != null) existing.setPurpose(app.getPurpose());
	    if (app.getSubmittedDate()   != null) existing.setSubmittedDate(app.getSubmittedDate());
	    if (app.getStatus()          != null) existing.setStatus(app.getStatus());
	    existing.setEnable(app.isEnable());
	    if (app.getBorrower() != null) {
	        if (existing.getBorrower() == null
	                || existing.getBorrower().getId() != app.getBorrower().getId()) {
	            existing.setBorrower(app.getBorrower());      // swap borrower
	        } else {
	            Borrower src = app.getBorrower();
	            Borrower dst = existing.getBorrower();
	            if (src.getFirstName() != null) dst.setFirstName(src.getFirstName());
	            if (src.getLastName()  != null) dst.setLastName(src.getLastName());
	            if (src.getEmail()     != null) dst.setEmail(src.getEmail());
	            if (src.getPhone()     != null) dst.setPhone(src.getPhone());
	        }
	    }

	    return appRepo.save(existing);
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

	// ApplicationServiceImpl.java
	@Override
	public boolean delete(int id) {
	    if (appRepo.existsById(id)) {
	        appRepo.deleteById(id);
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
		return appRepo.findByBorrower_LastNameContainingIgnoreCase(name);
	}

	public List<Application> findByBorrowerLastName(String name) {
		return appRepo.findByBorrower_LastNameContainingIgnoreCase(name);
	}

	public List<Application> findByLoanNumber(Integer loanId) {
		return appRepo.findByLoanNumber(loanId);
	}

	@Override
	public List<Application> findAllEnabled() {
		return appRepo.findByEnableTrue();
	}

}
