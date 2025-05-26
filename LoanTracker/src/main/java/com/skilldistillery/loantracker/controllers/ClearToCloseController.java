package com.skilldistillery.loantracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.ClearToClose;
import com.skilldistillery.loantracker.services.ClearToCloseService;

@RestController
	@RequestMapping("api/ctc")
	@CrossOrigin({"*", "http://localhost"})
	public class ClearToCloseController {

	    @Autowired
	    private ClearToCloseService service;

	    @GetMapping
	    public List<ClearToClose> index() {
	        return service.findAll();
	    }

	    @GetMapping("/completed")
	    public List<ClearToClose> completed() {
	        return service.findByCtcDateIsNotNull();
	    }
}
