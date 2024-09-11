package com.fitnesstracker.dto;

public class LoginResponseDTO {
	private Long id;
    private String userName;
    private String userEmail;

    // Getters and Setters
    

    public String getUserName() {
        return userName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
	
	

}
