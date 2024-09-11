package com.fitnesstracker.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class GoalsDTO {
	@Id
	private Long goalId;
	
	@NotBlank(message = "User id cannot be blank")
	private Long userId;
	
	@NotBlank(message = "Activity type id cannot be blank")
	private Long activityTypeId;
	
	@NotBlank(message = "Duration cannot be blank")
	private Long durationMinutes;

	   
	    public GoalsDTO() {}

	    public GoalsDTO(Long goalId, Long userId, Long activityTypeId, Long durationMinutes) {
	        this.goalId = goalId;
	        this.userId = userId;
	        this.activityTypeId = activityTypeId;
	        this.durationMinutes = durationMinutes;
	    }

	   
	    public Long getGoalId() {
	        return goalId;
	    }

	    public void setGoalId(Long goalId) {
	        this.goalId = goalId;
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

	    public Long getDurationMinutes() {
	        return durationMinutes;
	    }

	    public void setDurationMinutes(Long durationMinutes) {
	        this.durationMinutes = durationMinutes;
	    }	    
}
