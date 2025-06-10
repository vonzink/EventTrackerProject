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
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ClearToCloseController {

    @Autowired
    private ClearToCloseService clearToCloseService;

    @GetMapping({"ctc", "ctc/"})
    public List<ClearToClose> index() {
        return clearToCloseService.findAll();
    }

    @GetMapping("ctc/cleared")
    public List<ClearToClose> getClearedToClose() {
        return clearToCloseService.findByCtcDateIsNotNull();
    }
}