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
@Table(name = "meals")
public class Meals {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private Users user;

    @Column(name = "meal_time", nullable = false)
    private LocalTime mealTime;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "calories_consumed", nullable = false)
    private Double caloriesConsumed;

    // Constructors, Getters, and Setters
    public Meals() {}

    public Meals(Users user, LocalTime mealTime, LocalDate mealDate, Double caloriesConsumed) {
        this.user = user;
        this.mealTime = mealTime;
        this.mealDate = mealDate;
        this.caloriesConsumed = caloriesConsumed;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(LocalTime mealTime) {
        this.mealTime = mealTime;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public Double getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(Double caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }


}
