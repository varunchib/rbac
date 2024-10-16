package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup"; // This should correspond to your view file name (e.g., signup.html)
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password); // Remember to hash the password before saving

        userService.signUp(user);

        return "Registration Successful! Welcome, " + username + "! Your account has been created successfully.";
    }
}