package com.fitnesstracker.dto;

public class UserActivityDetailsResponseDTO {
	private String userName;
    private String activityName; // Activity type name
    private Long durationMinutes;
    private Double caloriesBurned;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
	public UserActivityDetailsResponseDTO() {

	}
	public Long getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationInMinutes(Long durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public Double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public UserActivityDetailsResponseDTO(String userName, String activityName, Long durationMinutes,
			Double caloriesBurned) {
		super();
		this.userName = userName;
		this.activityName = activityName;
		this.durationMinutes = durationMinutes;
		this.caloriesBurned = caloriesBurned;
	}
	
	
	
}
