package com.venture.rbac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venture.rbac.entity.User;
import com.venture.rbac.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User signUp(User user) {
        if (user == null) {
            throw new RuntimeException("User cannot be null");
        }
        
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }
        
        // Check if user already exists
        User existingUser = userRepository.findByName(user.getName());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }

        user.setIsAdmin(false);
        user.setIsManager(false);
        user.setIsUser(true);
        return userRepository.save(user);
    }

    public User findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }
        return userRepository.findByName(name);
    }

	public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        User user = userRepository.findByName(username);
        if (user == null) {
            logger.warn("No user found with username: {}", username);
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.getIsAdmin()) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (user.getIsManager()) authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        if (user.getIsUser()) authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(
            user.getName(), 
            user.getPassword(), 
            authorities
        );
    }

    public User signIn(String name, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        User user = userRepository.findByName(name);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}