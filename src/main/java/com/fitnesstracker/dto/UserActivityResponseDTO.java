package com.fitnesstracker.dto;

import java.time.LocalDate;

public class UserActivityResponseDTO {
	private Long activityId;
    private String activityName;
    private LocalDate activityDate;
    private Double caloriesBurned;
    private Long duration;
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public LocalDate getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}
		
	
	public Double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	

	public UserActivityResponseDTO(Long activityId, String activityName, LocalDate activityDate, Double caloriesBurned,
			Long duration) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDate = activityDate;
		this.caloriesBurned = caloriesBurned;
		this.duration = duration;
	}
	public UserActivityResponseDTO() {
		
	}
	
    

}
