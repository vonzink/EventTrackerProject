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

import com.skilldistillery.loantracker.entities.User;
import com.skilldistillery.loantracker.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"users", "user/"})
    public List<User> index(HttpServletResponse res) {
        List<User> users = userService.findAll();
        if (users == null || users.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
        }
        return users;
    }

    @GetMapping("users/{id}")
    public User show(@PathVariable("id") int id, HttpServletResponse res) {
        User user = userService.findById(id);
        if (user == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
        }
        return user;
    }

    @PostMapping({"users", "user/"})
    public User create(@RequestBody User user, HttpServletResponse res) {
        try {
            user = userService.create(user);
            res.setStatus(HttpServletResponse.SC_CREATED); // 201
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            user = null;
        }
        return user;
    }

    @PutMapping("users/{id}")
    public User update(@PathVariable("id") int id, @RequestBody User user, HttpServletResponse res) {
        User updated = userService.update(id, user);
        if (updated == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            res.setStatus(HttpServletResponse.SC_OK);
        }
        return updated;
    }

    @DeleteMapping("users/{id}")
    public void delete(@PathVariable("id") int id, HttpServletResponse res) {
        try {
            boolean deleted = userService.delete(id);
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
