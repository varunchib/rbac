package com.venture.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venture.rbac.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
