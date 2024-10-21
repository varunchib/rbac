package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@Controller
public class SignUpController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }
    
    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        
        try {
            userService.signUp(user);
            return "Registration Successful! <a href='/signin'>Click here to Sign In</a>";
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }
}