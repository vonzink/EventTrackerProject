package com.skilldistillery.loantracker.services;

import java.util.List;

import com.skilldistillery.loantracker.entities.User;

public interface UserService {

	List<User> findAll(); 
	User findById(int id);
	User update(int id, User user);
	boolean delete(int id);
	User create(User user); 
}

