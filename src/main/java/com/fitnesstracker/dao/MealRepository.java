package com.fitnesstracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.Meals;

@Repository
public interface MealRepository extends JpaRepository<Meals,Long> {

}
