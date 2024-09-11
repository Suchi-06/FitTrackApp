package com.fitnesstracker.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_type")
public class ActivityType {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "activity_type_id")
	    private Long activityTypeId;

	    @Column(name = "description", nullable = false)
	    private String name;

	    @JsonManagedReference
	    @OneToMany(mappedBy = "activityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<UserActivity> userActivities;

	    @JsonManagedReference
	    @OneToMany(mappedBy = "activityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Goals> goals;

	    // Constructors, Getters, and Setters
	    public void UserActivityType() {}

	    public void UserActivityType(String name) {
	        this.name = name;
	    }

	    public Long getActivityTypeId() {
	        return activityTypeId;
	    }

	    public void setActivityTypeId(Long activityTypeId) {
	        this.activityTypeId = activityTypeId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<UserActivity> getUserActivities() {
	        return userActivities;
	    }

	    public void setUserActivities(List<UserActivity> userActivities) {
	        this.userActivities = userActivities;
	    }

	    public List<Goals> getGoals() {
	        return goals;
	    }

	    public void setGoals(List<Goals> goals) {
	        this.goals = goals;
	    }
	

}
