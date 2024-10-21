package com.venture.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.venture.rbac.entity.User;
import com.venture.rbac.service.UserService;

@Controller
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showSignInPage() {
        return "signin";
    }

    @PostMapping
    public String processSignIn(@RequestParam("username") String name,
                                @RequestParam("password") String password,
                                Model model) {
        try {
            User user = userService.signIn(name, password);
            model.addAttribute("user", user);
            if (user.getIsAdmin()) {
                return "redirect:/dashboard";
            } else if (user.getIsManager()) {
                return "redirect:/dashboard";
            } else {
                return "redirect:/dashboard";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "signin";
        }
    }
}