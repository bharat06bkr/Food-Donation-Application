package com.example.fooddonation.controller;

import com.example.fooddonation.model.User;
import com.example.fooddonation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.login(user.getEmail(), user.getPassword()).isPresent()) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
        userService.register(user);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        return userService.login(user.getEmail(), user.getPassword())
                .map(u -> {
                    session.setAttribute("currentUser", u);
                    if (u.getRole() == User.Role.DONOR) {
                        return "redirect:/donor/dashboard";
                    } else {
                        return "redirect:/receiver/dashboard";
                    }
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid email or password!");
                    return "login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
