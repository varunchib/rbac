package com.venture.rbac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;

	@Column(name = "is_admin")
	private boolean isAdmin;

	@Column(name = "is_manager")
	private boolean isManager;

	@Column(name = "is_user")
	private boolean isUser;
	
	public User() {
		
	}
	
	public User(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isManager = isManager;
		this.isUser = isUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
	public boolean getIsUser() {
		return isUser;
	}
	public void setIsUser(boolean isUser) {
		this.isUser = isUser;
	}
}
