package com.fitnesstracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesstracker.dao.ActivityTypeRepository;
import com.fitnesstracker.dao.UserActivityRepository;
import com.fitnesstracker.dao.UserRepository;
import com.fitnesstracker.dto.UserActivityDTO;
import com.fitnesstracker.dto.UserActivityDetailsResponseDTO;
import com.fitnesstracker.dto.UserActivityResponseDTO;
import com.fitnesstracker.entity.ActivityType;
import com.fitnesstracker.entity.UserActivity;
import com.fitnesstracker.entity.Users;
import com.fitnesstracker.exception.ActivityTypeNotFoundException;
import com.fitnesstracker.exception.ResourceNotFoundException;
import com.fitnesstracker.exception.UserNotFoundException;

@Service
public class UserActivityService {
		
	@Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityTypeRepository activityTypeRepository;

 // Method to convert UserActivity entity to UserActivityDTO
    public UserActivityDTO userActivityEntityToDTO(UserActivity userActivity) {
        UserActivityDTO userActivityDTO = new UserActivityDTO();

        userActivityDTO.setActivityId(userActivity.getActivityId());
        userActivityDTO.setUserId(userActivity.getUser().getId());
        userActivityDTO.setActivityTypeId(userActivity.getActivityType().getActivityTypeId());
        userActivityDTO.setActivityDate(userActivity.getActivityDate());
        userActivityDTO.setStartTime(userActivity.getStartTime());
        userActivityDTO.setEndTime(userActivity.getEndTime());
        userActivityDTO.setCaloriesBurned(userActivity.getCaloriesBurned());

        return userActivityDTO;
    }

    // Method to convert UserActivityDTO to UserActivity entity
    public UserActivity userActivityDTOToEntity(UserActivityDTO userActivityDTO) {
        UserActivity userActivity = new UserActivity();

        userActivity.setActivityId(userActivityDTO.getActivityId());

        // Fetch Users entity using userId from UserActivityDTO
        Users user = userRepository.findById(userActivityDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + userActivityDTO.getUserId()));
        userActivity.setUser(user);

        // Fetch ActivityType entity using activityTypeId from UserActivityDTO
        ActivityType activityType = activityTypeRepository.findById(userActivityDTO.getActivityTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Activity Type ID: " + userActivityDTO.getActivityTypeId()));
        userActivity.setActivityType(activityType);

        userActivity.setActivityDate(userActivityDTO.getActivityDate());
        userActivity.setStartTime(userActivityDTO.getStartTime());
        userActivity.setEndTime(userActivityDTO.getEndTime());
        userActivity.setCaloriesBurned(userActivityDTO.getCaloriesBurned());

        return userActivity;
    }
    
	public UserActivityDTO addUserActivity(UserActivityDTO userActivityDTO) {
        Long userId = userActivityDTO.getUserId();
        Long activityTypeId = userActivityDTO.getActivityTypeId();

        // Check if user exists
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // Check if activity type exists
        ActivityType activityType = activityTypeRepository.findById(activityTypeId)
                .orElseThrow(() -> new ActivityTypeNotFoundException("ActivityType with ID " + activityTypeId + " not found"));

        // Map DTO to Entity
        UserActivity userActivity = userActivityDTOToEntity(userActivityDTO);
        userActivity.setUser(user);
        userActivity.setActivityType(activityType);

        // Save the entity
        userActivity = userActivityRepository.save(userActivity);

        // Convert Entity back to DTO and return
        return userActivityEntityToDTO(userActivity);
    }
	
	public List<UserActivityResponseDTO> getUserActivities(Long userId) {
	    List<Object[]> results = userActivityRepository.findUserActivities(userId);
	    
	    if (results.isEmpty()) {
	        throw new UserNotFoundException("No activities found for user with ID: " + userId);
	    }
	    
	    List<UserActivityResponseDTO> activities = new ArrayList<>();
	    
	    for (Object[] result : results) {
	        Long activityId = ((Number) result[0]).longValue();
	        String activityName = (String) result[1];
	        LocalDate activityDate = ((java.sql.Date) result[2]).toLocalDate();
	        Double caloriesBurned = ((Number) result[3]).doubleValue();
	        Long duration = ((Number) result[4]).longValue();
	        
	        activities.add(new UserActivityResponseDTO(activityId, activityName, activityDate, caloriesBurned, duration));
	    }
	    
	    return activities;
	}

	
	public List<UserActivityDetailsResponseDTO> getUserActivitiesByDate(String activityDate) {
	    List<Object[]> results = userActivityRepository.findUserActivitiesByActivityDate(activityDate);
	    
	    if (results.isEmpty()) {
	        throw new ResourceNotFoundException("No activities found for the date: " + activityDate);
	    }
	    
	    List<UserActivityDetailsResponseDTO> activities = new ArrayList<>();
	    
	    for (Object[] result : results) {
	        String userName = (String) result[0];
	        String activityName = (String) result[1];
	        Long durationMinutes = ((Number) result[2]).longValue();
	        Double caloriesBurned = ((Number) result[3]).doubleValue();
	        
	        activities.add(new UserActivityDetailsResponseDTO(userName, activityName, durationMinutes, caloriesBurned));
	    }
	    
	    return activities;
	}
}
