package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;
import com.venture.rbac.util.SessionManager;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SessionManager sessionManager;

    @GetMapping("/signin")
    public String showSignInPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String processSignIn(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model,
                              HttpSession session) {
        try {
            User user = userService.signIn(username, password);
            sessionManager.setUserSession(session, user);
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "signin";
        }
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        sessionManager.clearSession(session);
        return "redirect:/signin";
    }
}