package com.fitnesstracker.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsersDTO {
	@Id
    private Long id;

    @NotBlank(message = "User name cannot be blank")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long")
    private String userPassword;

    @NotNull(message = "Gender id cannot be null")
    private Long genderRefId;

    @NotNull(message = "User age cannot be null")
    @Column(name = "user_age", nullable = false)
    private Long userAge;

    @NotNull(message = "User height cannot be null") 
    private Double userHeight;

    @NotNull(message = "User weight cannot be null")
    private Double userWeight;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Pattern(
    	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
    	    message = "Invalid email format"
    	)
    private String userEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	

	public Long getGenderRefId() {
		return genderRefId;
	}

	public void setGenderRefId(Long genderRefId) {
		this.genderRefId = genderRefId;
	}

	public Long getUserAge() {
		return userAge;
	}

	public void setUserAge(Long userAge) {
		this.userAge = userAge;
	}

	public Double getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(Double userHeight) {
		this.userHeight = userHeight;
	}

	public Double getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(Double userWeight) {
		this.userWeight = userWeight;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

	public UsersDTO(Long id, @NotBlank(message = "User name cannot be blank") String userName,
			@NotBlank(message = "Password cannot be blank") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long") String userPassword,
			@NotNull(message = "Gender id cannot be null") Long genderRefId,
			@NotNull(message = "User age cannot be null") Long userAge,
			@NotNull(message = "User height cannot be null") Double userHeight,
			@NotNull(message = "User weight cannot be null") Double userWeight,
			@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format") String userEmail) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.genderRefId = genderRefId;
		this.userAge = userAge;
		this.userHeight = userHeight;
		this.userWeight = userWeight;
		this.userEmail = userEmail;
	}

	public UsersDTO() {
		
	}
    


}
