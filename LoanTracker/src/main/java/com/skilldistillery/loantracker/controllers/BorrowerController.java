package com.skilldistillery.loantracker.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.Borrower;
import com.skilldistillery.loantracker.services.BorrowerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

	@Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public List<Borrower> index(HttpServletResponse res) {
        List<Borrower> borrowers = borrowerService.findAll();
        if (borrowers == null || borrowers.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
        }
        return borrowers;
    }

    @GetMapping("{id}")
    public Borrower show(@PathVariable("id") int id, HttpServletResponse res) {
        Borrower borrower = borrowerService.findById(id);
        if (borrower == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
        return borrower;
    }

    @PostMapping
    public Borrower create(@RequestBody Borrower borrower, HttpServletResponse res) {
        try {
        	 borrower.setCreatedAt(LocalDateTime.now());
            borrower = borrowerService.create(borrower);
            res.setStatus(HttpServletResponse.SC_CREATED); // 201
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            borrower = null;
        }
        return borrower;
    }

    @PutMapping("{id}")
    public Borrower update(@PathVariable("id") int id, @RequestBody Borrower borrower, HttpServletResponse res) {
        try {
            borrower = borrowerService.update(id, borrower);
            if (borrower == null) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            }
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            borrower = null;
        }
        return borrower;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id, HttpServletResponse res) {
        try {
            boolean deleted = borrowerService.delete(id);
            if (deleted) {
                res.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
            } else {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            }
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }
    }
}
