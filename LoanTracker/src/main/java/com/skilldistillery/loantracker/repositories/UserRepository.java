package com.skilldistillery.loantracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

		User findByUsername(String username);
		List<User> findByFirstNameContainingIgnoreCase(String firstName);
		List<User> findByLastNameContainingIgnoreCase(String lastName);
		List<User> findByEmailContainingIgnoreCase(String email);
		List<User> findByRoleIgnoreCase(String role);
	  
}
