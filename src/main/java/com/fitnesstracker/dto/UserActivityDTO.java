package com.fitnesstracker.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class UserActivityDTO {
	@Id
    private Long activityId;
	
	@NotNull(message = "User id cannot be null")
    private Long userId;
	
	@NotNull(message = "Activity type id cannot be null")
    private Long activityTypeId;
	
	@NotNull(message = "Activity date cannot be null")
    private LocalDate activityDate;
	
	@NotNull(message = "Start time id cannot be null")
    private LocalTime startTime;
	
	@NotNull(message = "End time cannot be null")
    private LocalTime endTime;
    
	@NotNull(message = "Calories burned cannot be null")
    private Double caloriesBurned;

   
    public UserActivityDTO() {}

    public UserActivityDTO(Long activityId, Long userId, Long activityTypeId, LocalDate activityDate, LocalTime startTime, LocalTime endTime, Double caloriesBurned) {
        this.activityId = activityId;
        this.userId = userId;
        this.activityTypeId = activityTypeId;
        this.activityDate = activityDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.caloriesBurned = caloriesBurned;
    }

   
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Long activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
