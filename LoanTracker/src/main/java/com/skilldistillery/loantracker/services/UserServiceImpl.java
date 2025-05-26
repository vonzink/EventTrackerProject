package com.skilldistillery.loantracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.loantracker.entities.User;
import com.skilldistillery.loantracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Optional<User> findById(int id) {
		Optional<User> opt = userRepo.findById(id);
		return Optional.of(opt.orElse(null));
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return Optional.of(userRepo.findByUsername(username));
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		return userRepo.findByFirstNameContainingIgnoreCase(firstName);
	}

	@Override
	public List<User> findByLastName(String lastName) {
		return userRepo.findByLastNameContainingIgnoreCase(lastName);
	}

	@Override
	public List<User> findByEmail(String email) {
		return userRepo.findByEmailContainingIgnoreCase(email);
	}

	@Override
	public List<User> findByRole(String role) {
		return userRepo.findByRoleIgnoreCase(role);
	}

	@Override
	public User create(User user) {
		return userRepo.save(user);
	}

	@Override
	public User update(int id, User user) {
		Optional<User> opt = userRepo.findById(id);
		if (opt.isPresent()) {
			User existing = opt.get();
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setUsername(user.getUsername());
			existing.setEmail(user.getEmail());
			existing.setPassword(user.getPassword());
			existing.setRole(user.getRole());
			return userRepo.save(existing);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
