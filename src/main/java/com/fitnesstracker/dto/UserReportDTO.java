package com.fitnesstracker.dto;

import java.time.LocalDate;

public class UserReportDTO {
	private Long userId;
    private String userName;
    private LocalDate reportDate;
    private Integer totalCaloriesConsumed;
    private Integer totalCaloriesBurned;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	
	public UserReportDTO(Long userId, String userName, LocalDate reportDate, Integer totalCaloriesConsumed,
			Integer totalCaloriesBurned) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.reportDate = reportDate;
		this.totalCaloriesConsumed = totalCaloriesConsumed;
		this.totalCaloriesBurned = totalCaloriesBurned;
	}
	public Integer getTotalCaloriesConsumed() {
		return totalCaloriesConsumed;
	}
	public void setTotalCaloriesConsumed(Integer totalCaloriesConsumed) {
		this.totalCaloriesConsumed = totalCaloriesConsumed;
	}
	public Integer getTotalCaloriesBurned() {
		return totalCaloriesBurned;
	}
	public void setTotalCaloriesBurned(Integer totalCaloriesBurned) {
		this.totalCaloriesBurned = totalCaloriesBurned;
	}
	public UserReportDTO() {
		
	}

}
