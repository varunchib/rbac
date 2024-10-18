package com.venture.rbac.controller;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;
import com.venture.rbac.util.SessionManager;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
        User user = sessionManager.getUserFromSession(session);
        
        // Check if user is logged in
        if (user == null) {
            // Redirect to sign-in page if no user is found in session
            return "redirect:/signin";
        }
        
        // Add user details to the model if needed
        model.addAttribute("user", user);

        if ("Y".equals(user.getAdmin())) {
            List<User> allUsers = userService.getAll();
            model.addAttribute("users", allUsers);
            return "admin"; // This will render admin-dashboard.html
        } else {
            return "dashboard"; // This will render user-dashboard.html
        }
    }
}