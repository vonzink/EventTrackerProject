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

import com.skilldistillery.loantracker.entities.Documentation;
import com.skilldistillery.loantracker.services.DocumentationService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class DocumentationController {

    @Autowired
    private DocumentationService documentationService;

    @GetMapping({"docs", "docs/"})
    public List<Documentation> index() {
        return documentationService.findAll();
    }

    @GetMapping("docs/{id}")
    public Documentation getById(@PathVariable("id") int id, HttpServletResponse res) {
        Documentation doc = documentationService.findById(id);
        if (doc == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return doc;
    }

    @PostMapping("docs")
    public Documentation create(@RequestBody Documentation documentation, HttpServletResponse res) {
        try {
            Documentation created = documentationService.create(documentation);
            res.setStatus(HttpServletResponse.SC_CREATED);
            return created;
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @PutMapping("docs/{id}")
    public Documentation update(@PathVariable("id") int id, @RequestBody Documentation documentation, HttpServletResponse res) {
        Documentation updated = documentationService.update(id, documentation);
        if (updated == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return updated;
    }

    @DeleteMapping("docs/{id}")
    public void delete(@PathVariable("id") int id, HttpServletResponse res) {
        if (documentationService.delete(id)) {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}