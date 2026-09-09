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
@RequestMapping("/receiver")
public class ReceiverController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/dashboard")
    public String receiverDashboard(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.RECEIVER) {
            return "redirect:/login";
        }
        List<Food> availableFoods = foodService.getAvailableFood();
        model.addAttribute("availableFoods", availableFoods);
        model.addAttribute("currentUser", currentUser);
        return "receiver_dashboard";
    }

    @PostMapping("/book-food/{foodId}")
    public String bookFood(@PathVariable Long foodId, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || currentUser.getRole() != User.Role.RECEIVER) {
            return "redirect:/login";
        }
        boolean success = foodService.bookFood(foodId, currentUser);
        if (!success) {
            model.addAttribute("error", "Food already booked or not available!");
        }
        return "redirect:/receiver/dashboard";
    }
}
