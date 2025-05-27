package com.skilldistillery.loantracker.controllers;

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

import com.skilldistillery.loantracker.entities.Declined;
import com.skilldistillery.loantracker.services.DeclinedService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class DeclinedController {

    @Autowired
    private DeclinedService declinedService;

    @GetMapping({"declined", "declined/"})
    public List<Declined> index() {
        return declinedService.findAll();
    }

    @GetMapping("declined/{id}")
    public Declined getById(@PathVariable int id, HttpServletResponse res) {
        Declined declined = declinedService.findById(id);
        if (declined == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return declined;
    }

    @GetMapping("declined/application/{appId}")
    public List<Declined> getByApplicationId(@PathVariable("appId") int appId) {
        return declinedService.findByApplicationId(appId);
    }

    @PostMapping("declined")
    public Declined create(@RequestBody Declined declined, HttpServletResponse res) {
        try {
            Declined created = declinedService.create(declined);
            res.setStatus(HttpServletResponse.SC_CREATED);
            return created;
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}