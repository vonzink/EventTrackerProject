package com.skilldistillery.loantracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.loantracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
