package com.fitnesstracker.dto;

public class GoalResponseDTO {
	 private Long goalId;
	 private String activityName;
	 private Integer duration;
	public Long getGoalId() {
		return goalId;
	}
	public void setGoalId(Long goalId) {
		this.goalId = goalId;
	}
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public GoalResponseDTO(Long goalId, String activityName, Integer duration) {
		super();
		this.goalId = goalId;
		this.activityName = activityName;
		this.duration = duration;
	}
	public GoalResponseDTO() {
			}

}
