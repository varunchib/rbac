package com.venture.rbac.entity;

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
    private String admin = "N";    // Default value
    private String manager = "N";  // Default value
    private String is_user = "Y";     // Default value
    
    public User() {
    }
    
    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    // Existing getters and setters
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
    
    // New getters and setters for role fields
    public String getAdmin() {
        return admin;
    }
    
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    public String getManager() {
        return manager;
    }
    
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    public String getUser() {
        return is_user;
    }
    
    public void setUser(String is_user) {
        this.is_user = is_user;
    }
}