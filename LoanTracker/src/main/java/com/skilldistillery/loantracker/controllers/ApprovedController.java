package com.skilldistillery.loantracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.Approved;
import com.skilldistillery.loantracker.services.ApprovedService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("api")
public class ApprovedController {

	@Autowired
	private ApprovedService approvedService; 
	
	@GetMapping({"approved", "approved/"})
	public List<Approved> index() {
		return approvedService.findAll(); 
	}
	@GetMapping("approved/{id}")
	public Optional<Approved> findById(@PathVariable("id") int id, HttpServletResponse res) {
		Optional<Approved> approved = approvedService.findById(id);
		if (approved  == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return approved ;
	}
	@GetMapping("approved/application/{appId}")
    public Approved findByApplicationId(@PathVariable("appId") int appId, HttpServletResponse res) {
        Optional<Approved> approved = approvedService.findByApplicationId(appId);
        if (approved.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return approved.get();
    }

    @PostMapping("approved")
    public Approved create(@RequestBody Approved approved, HttpServletResponse res) {
        try {
            Approved created = approvedService.create(approved);
            res.setStatus(HttpServletResponse.SC_CREATED);
            return created;
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @PutMapping("approved/{id}")
    public Approved update(@PathVariable("id") int id, @RequestBody Approved approved, HttpServletResponse res) {
        Approved updated = approvedService.update(id, approved);
        if (updated == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return updated;
    }
}

