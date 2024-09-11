package com.fitnesstracker.entity;

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
@Table(name = "goals")
public class Goals {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long goalId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "activity_type_id", nullable = false,referencedColumnName = "activity_type_id")
    private ActivityType activityType;

    @Column(name = "duration_minutes", nullable = false)
    private Long durationMinutes;

    // Constructors, Getters, and Setters
    public Goals() {}

    public Goals(Users user, ActivityType activityType, Long durationMinutes) {
        this.user = user;
        this.activityType = activityType;
        this.durationMinutes = durationMinutes;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
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

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Long durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

}
