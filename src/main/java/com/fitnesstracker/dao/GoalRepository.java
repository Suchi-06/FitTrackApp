package com.fitnesstracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.Goals;

@Repository
public interface GoalRepository extends JpaRepository<Goals,Long>{
	
	@Query(value = "SELECT g.goal_id, at.description, g.duration_minutes " +
            "FROM goals g " +
            "JOIN activity_type at ON g.activity_type_id = at.activity_type_id " +
            "WHERE g.user_id = :userId",
    nativeQuery = true)
	List<Object[]> findGoalsByUserId(@Param("userId") Long userId);


}
