package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @GetMapping("/signin")
    public String showSignInPage() {
        return "signin"; // Maps to signin.html or signin.jsp
    }

    @PostMapping("/signin")
    public @ResponseBody String processSignIn(@RequestParam("username") String username,
                                              @RequestParam("password") String password,
                                              Model model) {
        try {
            User user = userService.signIn(username, password);
            model.addAttribute("user", user);
            return "Login successful!"; // Return a string indicating successful login
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "Login failed!"; // Return a string indicating failed login
        }
    }
}