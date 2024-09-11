package com.fitnesstracker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
	
	 Optional<Users> findByUserEmail(String email);
	 Optional<Users> findById(Long id);
	 boolean existsByUserEmail(String email);
	 	  
	 @Query(value = "SELECT " +
		        "u.id AS user_id, " +
		        "u.user_name, " +
		        "COALESCE(ua.activity_date, m.meal_date) AS report_date, " +
		        "COALESCE(m.totalCaloriesConsumed, 0) AS totalCaloriesConsumed, " +
		        "COALESCE(ua.totalCaloriesBurned, 0) AS totalCaloriesBurned " +
		        "FROM users u " +
		        "LEFT JOIN ( " +
		        "SELECT user_id, activity_date, SUM(calories_burned) AS totalCaloriesBurned " +
		        "FROM user_activity " +
		        "WHERE user_id = :userId " +
		        "GROUP BY user_id, activity_date " +
		        ") ua ON u.id = ua.user_id " +
		        "LEFT JOIN ( " +
		        "SELECT user_id, meal_date, SUM(calories_consumed) AS totalCaloriesConsumed " +
		        "FROM meals " +
		        "WHERE user_id = :userId " +
		        "GROUP BY user_id, meal_date " +
		        ") m ON u.id = m.user_id AND (ua.activity_date = m.meal_date OR ua.activity_date IS NULL OR m.meal_date IS NULL) " +
		        "WHERE (ua.activity_date = :date OR m.meal_date = :date)",
		        nativeQuery = true)
		Object[] findUserReportByUserIdAndDate(@Param("userId") Long userId, @Param("date") String date);



	 
	 
	 
	 
	 
	 

}
