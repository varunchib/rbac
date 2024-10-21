package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@RestController
@RequestMapping("/api/auth") 
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
    // Fixed the parameter handling
    @PostMapping("/signin")
    public String signIn(@RequestParam("name") String name, @RequestParam("password") String password , 
    Model model) {  
        try {
            User user = userService.signIn(name, password);
            if (user.getIsAdmin()) {
                model.addAttribute("user", user);
                return "redirect:/dashboard";
            } else {
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}