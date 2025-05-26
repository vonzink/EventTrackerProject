package com.skilldistillery.loantracker.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.skilldistillery.loantracker.entities.Application;
import com.skilldistillery.loantracker.services.ApplicationService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class ApplicationController {

	@Autowired
	private ApplicationService appService;

	@GetMapping({ "applications", "applications/" })
	public List<Application> index() {
		return appService.findAll();
	}

	@GetMapping("applications/{id}")
	public Application findById(@PathVariable("id") int id, HttpServletResponse res) {
		Application app = appService.findById(id);
		if (app == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return app;
	}

	@PostMapping("applications")
	public Application create(@RequestBody Application app, HttpServletResponse res) {
		try {
			Application created = appService.create(app);
			res.setStatus(HttpServletResponse.SC_CREATED);
			return created;
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

	@PutMapping("applications/{id}")
	public Application update(@PathVariable("id") int id, @RequestBody Application app, HttpServletResponse res) {
		Application updated = appService.update(id, app);
		if (updated == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return updated;
	}

	@DeleteMapping("applications/{id}")
	public void delete(@PathVariable("id") int id, HttpServletResponse res) {
		if (appService.delete(id)) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	// 🔍 Search endpoints
	@GetMapping("applications/search/name")
	public List<Application> findByName(@RequestParam("name") String name) {
		return appService.findByBorrowerLastName(name);
	}

	@GetMapping("applications/search/address")
	public List<Application> findByAddress(@RequestParam("address") String address) {
		return appService.findByPropertyAddress(address);
	}

	@GetMapping("applications/search/status")
	public List<Application> findByStatus(@RequestParam("status") String status) {
		return appService.findByStatus(status);
	}
	@GetMapping("applications/search/dates")
	public List<Application> findByDateRange(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
		return appService.findBySubmittedDateRange(start, end);
	}
}