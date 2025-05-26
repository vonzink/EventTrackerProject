package com.skilldistillery.loantracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.skilldistillery.loantracker.entities.Documentation;
import com.skilldistillery.loantracker.services.DocumentationService;

@RestController
@RequestMapping("api/documentation")
public class DocumentationController {

    @Autowired
    private DocumentationService docService;

    @GetMapping
    public List<Documentation> index() {
        return docService.findAll();
    }

    @GetMapping("{id}")
    public Documentation getById(@PathVariable("id") int id) {
        return docService.findById(id);
    }

    @PostMapping
    public Documentation create(@RequestBody Documentation documentation) {
        return docService.create(documentation);
    }

    @PutMapping("{id}")
    public Documentation update(@PathVariable("id") int id, @RequestBody Documentation documentation) {
        return docService.update(id, documentation);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") int id) {
        return docService.delete(id);
    }
}
