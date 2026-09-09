package com.example.fooddonation.controller;

import com.example.fooddonation.model.Food;
import com.example.fooddonation.model.User;
import com.example.fooddonation.service.FoodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/dashboard")
    public String donorDashboard(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.DONOR) {
            return "redirect:/login";
        }
        List<Food> foods = foodService.getFoodsByDonor(currentUser);
        model.addAttribute("foods", foods);
        model.addAttribute("food", new Food());
        model.addAttribute("currentUser", currentUser);
        return "donor_dashboard";
    }

    @PostMapping("/post-food")
    public String postFood(@ModelAttribute Food food, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.DONOR) {
            return "redirect:/login";
        }
        food.setDonor(currentUser);
        foodService.postFood(food);
        return "redirect:/donor/dashboard";
    }
}
