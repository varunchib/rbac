package com.venture.rbac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venture.rbac.entity.User;
import com.venture.rbac.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User signUp(User user) {
		return userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	} 
	
	public User signIn(String name, String password) {
		User user = userRepository.findByName(name);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("Invalid name or Password");
	}
	

}
