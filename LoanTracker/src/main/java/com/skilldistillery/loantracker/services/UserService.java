package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.loantracker.entities.User;

public interface UserService {

	Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByEmail(String email);
    List<User> findByRole(String role);
    User create(User user);
    User update(int id, User user);
    boolean delete(int id);
}

