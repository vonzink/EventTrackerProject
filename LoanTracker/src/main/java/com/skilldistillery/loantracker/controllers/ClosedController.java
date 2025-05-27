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

import com.skilldistillery.loantracker.entities.Closed;
import com.skilldistillery.loantracker.services.ClosedService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class ClosedController {

    @Autowired
    private ClosedService closedService;

    @GetMapping({"closed", "closed/"})
    public List<Closed> index() {
        return closedService.findAll();
    }

    @GetMapping("closed/{id}")
    public Closed getById(@PathVariable("id") int id, HttpServletResponse res) {
        Optional<Closed> closed = closedService.findById(id);
        if (closed.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return closed.get();
    }

    @GetMapping("closed/application/{appId}")
    public Closed getByApplicationId(@PathVariable("appId") int appId, HttpServletResponse res) {
        Optional<Closed> closed = closedService.findByApplicationId(appId);
        if (closed.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return closed.get();
    }

    @PostMapping("closed")
    public Closed create(@RequestBody Closed closed, HttpServletResponse res) {
        try {
            Closed created = closedService.create(closed);
            res.setStatus(HttpServletResponse.SC_CREATED);
            return created;
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}

