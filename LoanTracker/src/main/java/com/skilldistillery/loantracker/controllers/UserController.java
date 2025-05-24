package com.skilldistillery.loantracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping({"users", "users/"})
	public List<User> index(){
		return userService.findAll(); 
	}
	@GetMapping("users/{userId}")
	public User findById(@PathVariable("userId") int userId, HttpServletResponse res) {
		User user = userService.findById(userId); 
		if(user == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND); 
		}
		return user;		
	}
}
