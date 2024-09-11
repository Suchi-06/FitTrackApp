package com.fitnesstracker.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "User name cannot be blank")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long")
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ref_id", nullable = false,referencedColumnName = "ref_Id")
    private GenderRef genderRefId;

    @NotNull(message = "User age cannot be null")
    @Column(name = "user_age", nullable = false)
    private Long userAge;

    @NotNull(message = "User height cannot be null")
    @Column(name = "user_height", nullable = false)
    private Double userHeight;

    @NotNull(message = "User weight cannot be null")
    @Column(name = "user_weight", nullable = false)
    private Double userWeight;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Pattern(
    	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
    	    message = "Invalid email format"
    	)
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goals> goals;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Meals> meals;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserActivity> userActivities;

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

	

	public GenderRef getGenderRefId() {
		return genderRefId;
	}

	public void setGenderRefId(GenderRef genderRefId) {
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

	public List<Goals> getGoals() {
		return goals;
	}

	public void setGoals(List<Goals> goals) {
		this.goals = goals;
	}

	public List<Meals> getMeals() {
		return meals;
	}

	public void setMeals(List<Meals> meals) {
		this.meals = meals;
	}

	public List<UserActivity> getUserActivities() {
		return userActivities;
	}

	public void setUserActivities(List<UserActivity> userActivities) {
		this.userActivities = userActivities;
	}

	

	public Users(Long id, @NotBlank(message = "User name cannot be blank") String userName,
			@NotBlank(message = "Password cannot be blank") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long") String userPassword,
			GenderRef genderRefId, @NotNull(message = "User age cannot be null") Long userAge,
			@NotNull(message = "User height cannot be null") Double userHeight,
			@NotNull(message = "User weight cannot be null") Double userWeight,
			@NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format") String userEmail,
			List<Goals> goals, List<Meals> meals, List<UserActivity> userActivities) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.genderRefId = genderRefId;
		this.userAge = userAge;
		this.userHeight = userHeight;
		this.userWeight = userWeight;
		this.userEmail = userEmail;
		this.goals = goals;
		this.meals = meals;
		this.userActivities = userActivities;
	}

	public Users() {
		
	}
	

}
    

    