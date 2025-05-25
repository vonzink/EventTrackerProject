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
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User update(int id, User user) {
		Optional<User> existingOpt = userRepository.findById(id);
		if (existingOpt.isPresent()) {
			User existing = existingOpt.get();
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setEmail(user.getEmail());
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			existing.setRole(user.getRole());
			return userRepository.save(existing);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

}
