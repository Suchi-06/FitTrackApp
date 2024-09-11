package com.fitnesstracker.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity,Long> {
	
	@Query(value = "SELECT ua.activity_id, at.description, ua.activity_date, ua.calories_burned, TIMESTAMPDIFF(MINUTE, ua.start_time, ua.end_time) AS durationMinutes " +
            "FROM user_activity ua " +
            "JOIN activity_type at ON ua.activity_type_id = at.activity_type_id " +
            "WHERE ua.user_id = :userId " +
            "ORDER BY ua.activity_date DESC", 
     nativeQuery = true)
    List<Object[]> findUserActivities(@Param("userId") Long userId);


	 
    @Query(value = "SELECT u.user_name, at.description, TIMESTAMPDIFF(MINUTE, ua.start_time, ua.end_time) AS durationMinutes, ua.calories_burned " +
            "FROM user_activity ua " +
            "JOIN users u ON ua.user_id = u.id " +
            "JOIN activity_type at ON ua.activity_type_id = at.activity_type_id " +
            "WHERE ua.activity_date = :activityDate " +
            "ORDER BY ua.calories_burned DESC",
    nativeQuery = true)
    List<Object[]> findUserActivitiesByActivityDate(@Param("activityDate") String activityDate);


	
   



}
