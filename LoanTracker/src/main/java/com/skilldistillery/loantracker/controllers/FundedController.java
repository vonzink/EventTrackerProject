package com.skilldistillery.loantracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.loantracker.entities.Funded;
import com.skilldistillery.loantracker.services.FundedService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class FundedController {

    @Autowired
    private FundedService fundedService;

    @GetMapping({"funded", "funded/"})
    public List<Funded> index() {
        return fundedService.findAll();
    }

    @GetMapping("funded/{id}")
    public Funded getById(@PathVariable("id") int id, HttpServletResponse res) {
        Funded funded = fundedService.findById(id);
        if (funded == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return funded;
    }

    @GetMapping("funded/application/{appId}")
    public Funded getByApplicationId(@PathVariable("id") int appId, HttpServletResponse res) {
        Funded funded = fundedService.findByApplicationId(appId);
        if (funded == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return funded;
    }

    @PostMapping("funded")
    public Funded create(@RequestBody Funded funded, HttpServletResponse res) {
        try {
            Funded created = fundedService.create(funded);
            res.setStatus(HttpServletResponse.SC_CREATED);
            return created;
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
