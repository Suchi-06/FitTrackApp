package com.fitnesstracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class MealsDTO {
	@Id
	private Long mealId;
	
	@NotNull(message = "User id cannot be null")
    private Long userId;
	
	@NotNull(message = "Meal time cannot be null")
    private LocalTime mealTime;
	
	@NotNull(message = "meal Date cannot be null")
    private LocalDate mealDate;
	
	@NotNull(message = "Calories consumed cannot be null")
    private Double caloriesConsumed;

    
    public MealsDTO() {}

    public MealsDTO(Long mealId, Long userId, LocalTime mealTime, LocalDate mealDate, Double caloriesConsumed) {
        this.mealId = mealId;
        this.userId = userId;
        this.mealTime = mealTime;
        this.mealDate = mealDate;
        this.caloriesConsumed = caloriesConsumed;
    }

    
    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(LocalTime mealTime) {
        this.mealTime = mealTime;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public Double getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(Double caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

}
