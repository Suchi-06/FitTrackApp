package com.fitnesstracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesstracker.dao.ActivityTypeRepository;
import com.fitnesstracker.dao.GoalRepository;
import com.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.dto.GoalResponseDTO;
import com.fitnesstracker.dto.GoalsDTO;
import com.fitnesstracker.entity.ActivityType;
import com.fitnesstracker.entity.Goals;
import com.fitnesstracker.entity.Users;
import com.fitnesstracker.exception.ActivityTypeNotFoundException;
import com.fitnesstracker.exception.UserNotFoundException;

@Service
public class GoalService {
	 @Autowired
	    private GoalRepository goalRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private ActivityTypeRepository activityTypeRepository;
	    
	 // Method to convert Goals entity to GoalsDTO
	    public GoalsDTO goalsEntityToDTO(Goals goals) {
	        GoalsDTO goalsDTO = new GoalsDTO();

	        goalsDTO.setGoalId(goals.getGoalId());
	        goalsDTO.setUserId(goals.getUser().getId());
	        goalsDTO.setActivityTypeId(goals.getActivityType().getActivityTypeId());
	        goalsDTO.setDurationMinutes(goals.getDurationMinutes());

	        return goalsDTO;
	    }
	    
	 // Method to convert GoalsDTO to Goals entity
	    public Goals goalsDTOToEntity(GoalsDTO goalsDTO) {
	        Goals goals = new Goals();

	        goals.setGoalId(goalsDTO.getGoalId());

	        // Fetch Users entity using userId from GoalsDTO
	        Users user = userRepository.findById(goalsDTO.getUserId())
	                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + goalsDTO.getUserId()));
	        goals.setUser(user);

	        // Fetch ActivityType entity using activityTypeId from GoalsDTO
	        ActivityType activityType = activityTypeRepository.findById(goalsDTO.getActivityTypeId())
	                .orElseThrow(() -> new IllegalArgumentException("Invalid Activity Type ID: " + goalsDTO.getActivityTypeId()));
	        goals.setActivityType(activityType);

	        goals.setDurationMinutes(goalsDTO.getDurationMinutes());

	        return goals;
	    }

	  
	    
	    public GoalsDTO addGoal(GoalsDTO goalsDTO) {
	        Long userId = goalsDTO.getUserId();
	        Long activityTypeId = goalsDTO.getActivityTypeId();

	        // Check if user exists
	        Users user = userRepository.findById(userId)
	                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

	        // Check if activity type exists
	        ActivityType activityType = activityTypeRepository.findById(activityTypeId)
	                .orElseThrow(() -> new ActivityTypeNotFoundException("ActivityType with ID " + activityTypeId + " not found"));

	        // Map DTO to Entity
	        Goals goals = goalsDTOToEntity(goalsDTO);
	        goals.setUser(user);
	        goals.setActivityType(activityType);

	        // Save the entity
	        goals = goalRepository.save(goals);

	        // Convert Entity back to DTO and return
	        return goalsEntityToDTO(goals);
	    }
	    
	    public List<GoalResponseDTO> getGoalsByUserId(Long userId) {
	      List<Object[]> results = goalRepository.findGoalsByUserId(userId);
	      
	      if (results.isEmpty()) {
		        throw new UserNotFoundException("No goals found for user with ID: " + userId);
		    }
	      
	      List<GoalResponseDTO> goals = new ArrayList<>();
	      
	      for (Object[] result : results) {
	    	  Long goalId = ((Number) result[0]).longValue();
		      String activityName = (String) result[1];
		      Integer duration = ((Number) result[2]).intValue();
		      
		      goals.add(new GoalResponseDTO(goalId, activityName, duration));
	      }
		return goals;
	      
	    }
}
