package com.example.fooddonation.service;

import com.example.fooddonation.model.Food;
import com.example.fooddonation.model.User;
import com.example.fooddonation.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food postFood(Food food) {
        food.setStatus(Food.Status.AVAILABLE);
        return foodRepository.save(food);
    }

    public boolean bookFood(Long foodId, User receiver) {
        Optional<Food> foodOpt = foodRepository.findById(foodId);
        if (foodOpt.isPresent() && foodOpt.get().getStatus() == Food.Status.AVAILABLE) {
            Food food = foodOpt.get();
            food.setStatus(Food.Status.BOOKED);
            food.setReceiver(receiver);
            foodRepository.save(food);
            return true;
        }
        return false;
    }

    public List<Food> getAvailableFood() {
        return foodRepository.findByStatus(Food.Status.AVAILABLE);
    }

    public List<Food> getFoodsByDonor(User donor) {
        return foodRepository.findByDonor(donor);
    }
}
