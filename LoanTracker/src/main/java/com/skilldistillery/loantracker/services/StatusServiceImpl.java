package com.skilldistillery.loantracker.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.entities.Status;
import com.skilldistillery.loantracker.entities.User;
import com.skilldistillery.loantracker.repositories.ApplicationRepository;
import com.skilldistillery.loantracker.repositories.StatusRepository;
import com.skilldistillery.loantracker.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepo; 	
	@Autowired
	private ApplicationRepository appRepo; 
	@Autowired
	private UserRepository userRepo; 
	
	
	
	@Override
	public Status addStatus(int appId, String StatusName, String notes, int userId) {
		Application app = appRepo.findById(appId).orElseThrow(); 
		User user = userRepo.findById(userId).orElseThrow(); 
		
		Status status = new Status(); 
		status.setStatus(StatusName);
		status.setApplication(app);
		status.setNotes(notes);
		status.setChangedBy(user);
		status.setChangedAt(LocalDateTime.now());
		
		return statusRepo.save(status);
	}

	@Override
	public List<Status> getStatusHistory(int appId) {
	    return statusRepo.findByApplicationId(appId);
	}

	@Override
	public Status create(Status status) {
		return statusRepo.save(status);
		
	}

}
