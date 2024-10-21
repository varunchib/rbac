package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (user.getIsAdmin()) {
            return "admin-dashboard";
        } else if (user.getIsManager()) {
            return "manager-dashboard";
        } else {
            return "user-dashboard";
        }
    }
}