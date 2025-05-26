package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.Status;

public interface StatusService {

	Status addStatus(int appId, String StatusName, String notes, int userId); 
	List<Status>  getStatusHistory(int appId); 
	 Status create(Status status);
}
