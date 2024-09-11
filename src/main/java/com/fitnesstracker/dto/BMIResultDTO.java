package com.fitnesstracker.dto;

public class BMIResultDTO {
	private Long userId;
    private String userName;
    private Double bmi;
    private String healthStatus;
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
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public BMIResultDTO(Long userId, String userName, Double bmi, String healthStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.bmi = bmi;
		this.healthStatus = healthStatus;
	}
	public BMIResultDTO() {
		
	}


}
