package com.skilldistillery.loantracker.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.entities.Status;
import com.skilldistillery.loantracker.services.ApplicationService;
import com.skilldistillery.loantracker.services.StatusService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("api")
public class StatusController {

	@Autowired
	private StatusService statusService; 
	@Autowired
	private ApplicationService appService; 
	
	@PostMapping("applications/{appId}/status")
	public Status createStatus(@PathVariable("appId") int appId, @RequestBody Status status, HttpServletResponse res) {
	    try {
	        Application app = appService.findById(appId);
	        if (app == null) {
	            res.setStatus(404);
	            return null;
	        }
	        status.setApplication(app);
	        status.setChangedAt(LocalDateTime.now()); // set timestamp here
	        return statusService.create(status);
	    } catch (Exception e) {
	        res.setStatus(400);
	        return null;
	    }
	}

	@GetMapping("/application/{appId}")
	public List<Status> getStatusHistory(@PathVariable("appId") int appId) {
		return statusService.getStatusHistory(appId);
	}
	
	
}
