package com.skilldistillery.loantracker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.Underwriting;
import com.skilldistillery.loantracker.services.UnderwritingService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/underwriting")
@CrossOrigin({ "*", "http://localhost/" })
public class UnderwritingController {

	@Autowired
	private UnderwritingService uwService;

	@GetMapping
	public List<Underwriting> index() {
		return uwService.findAll();
	}

	@GetMapping("{id}")
	public Underwriting findById(@PathVariable("id") int id, HttpServletResponse res) {
		Underwriting uw = uwService.findById(id);
		if (uw == null)
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return uw;
	}

	@GetMapping("name/{name}")
	public Optional<Underwriting> findByName(@PathVariable("name") String name, HttpServletResponse res) {
		Optional<Underwriting> uw = uwService.findByUnderwriterName(name);
		if (uw == null)
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return uw;
	}

	@GetMapping("decision/{decision}")
	public List<Underwriting> findByDecision(@PathVariable("decision") String decision, HttpServletResponse res) {
		List<Underwriting> uw = uwService.findByDecision(decision);
		if (uw.isEmpty())
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return uw;
	}

	@GetMapping("findings/{keyword}")
	public List<Underwriting> findByFindings(@PathVariable("keyword") String keyword, HttpServletResponse res) {
		List<Underwriting> uw = uwService.findByFindings(keyword);
		if (uw.isEmpty())
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return uw;
	}
	    @PostMapping
	    public Underwriting create(@RequestBody Underwriting underwriter, HttpServletResponse res) {
	        try {
	            return uwService.create(underwriter);
	        } catch (Exception e) {
	            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            return null;
	        }
	    }

	    @PutMapping("{id}")
	    public Underwriting update(@PathVariable("id") int id, @RequestBody Underwriting underwriter, HttpServletResponse res) {
	        Underwriting updated = uwService.update(id, underwriter);
	        if (updated == null) res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        return updated;
	    }

	    @DeleteMapping("{id}")
	    public void delete(@PathVariable("id") int id, HttpServletResponse res) {
	        if (uwService.delete(id)) {
	            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
	        } else {
	            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        }
	    }
	}

