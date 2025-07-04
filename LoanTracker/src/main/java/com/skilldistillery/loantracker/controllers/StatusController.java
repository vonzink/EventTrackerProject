package com.skilldistillery.loantracker.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin({ "*", "http://localhost/" })
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
	            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            return null;
	        }
	        status.setApplication(app);
	        status.setChangedAt(LocalDateTime.now());
	        return statusService.create(status);
	    } catch (Exception e) {
	        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return null;
	    }
	}

	@GetMapping("applications/{appId}/status")
	public List<Status> getStatusHistory(@PathVariable("appId") int appId) {
	    return statusService.getStatusHistory(appId);
	}
	
}
