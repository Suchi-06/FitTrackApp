package com.fitnesstracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesstracker.dao.MealRepository;
import com.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.dto.MealsDTO;
import com.fitnesstracker.entity.Meals;
import com.fitnesstracker.entity.Users;
import com.fitnesstracker.exception.UserNotFoundException;

@Service
public class MealService {
	@Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;
    
 // Method to convert Meals entity to MealsDTO
    public MealsDTO mealsEntityToDTO(Meals meals) {
        MealsDTO mealsDTO = new MealsDTO();

        mealsDTO.setMealId(meals.getMealId());
        mealsDTO.setUserId(meals.getUser().getId());
        mealsDTO.setMealTime(meals.getMealTime());
        mealsDTO.setMealDate(meals.getMealDate());
        mealsDTO.setCaloriesConsumed(meals.getCaloriesConsumed());

        return mealsDTO;
    }
    
 // Method to convert MealsDTO to Meals entity
    public Meals mealsDTOToEntity(MealsDTO mealsDTO) {
        Meals meals = new Meals();

        meals.setMealId(mealsDTO.getMealId());

        // Fetch Users entity using userId from MealsDTO
        Users user = userRepository.findById(mealsDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + mealsDTO.getUserId()));
        meals.setUser(user);

        meals.setMealTime(mealsDTO.getMealTime());
        meals.setMealDate(mealsDTO.getMealDate());
        meals.setCaloriesConsumed(mealsDTO.getCaloriesConsumed());

        return meals;
    }

  
    
    public MealsDTO addMeal(MealsDTO mealsDTO) {
        Long userId = mealsDTO.getUserId();

        // Check if user exists
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // Map DTO to Entity
        Meals meal = mealsDTOToEntity(mealsDTO);
        meal.setUser(user);

        // Save the entity
        meal = mealRepository.save(meal);

        // Convert Entity back to DTO and return
        return mealsEntityToDTO(meal);
    }


}
