package com.fitnesstracker.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_activity")
public class UserActivity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "activity_type_id", nullable = false,referencedColumnName = "activity_type_id")
    private ActivityType activityType;

    @Column(name = "activity_date", nullable = false)
    private LocalDate activityDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "calories_burned", nullable = false)
    private Double caloriesBurned;

    // Constructors, Getters, and Setters
    public UserActivity() {}

    public UserActivity(Users user, ActivityType activityType, LocalDate activityDate, LocalTime startTime, LocalTime endTime, Double caloriesBurned) {
        this.user = user;
        this.activityType = activityType;
        this.activityDate = activityDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.caloriesBurned = caloriesBurned;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }


}
