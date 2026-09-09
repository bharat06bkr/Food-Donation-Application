package com.example.fooddonation.repository;

import com.example.fooddonation.model.Food;
import com.example.fooddonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByStatus(Food.Status status);
    List<Food> findByDonor(User donor);
}
