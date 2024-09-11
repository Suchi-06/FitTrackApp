package com.fitnesstracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesstracker.dao.ActivityTypeRepository;
import com.fitnesstracker.dto.ActivityTypeDTO;
import com.fitnesstracker.entity.ActivityType;

@Service
public class ActivityTypeService {
	 

	@Autowired
    private ActivityTypeRepository activityTypeRepository;
	
	 public ActivityTypeDTO activityTypeEntityToDTO(ActivityType activityType) {
	        ActivityTypeDTO dto = new ActivityTypeDTO();
	        dto.setActivityTypeId(activityType.getActivityTypeId());
	        dto.setDescription(activityType.getName());
	        return dto;
	    }

	    // Manually map ActivityTypeDTO to ActivityType entity
	    public ActivityType activityTypeDTOToEntity(ActivityTypeDTO dto) {
	        ActivityType activityType = new ActivityType();
	        activityType.setActivityTypeId(dto.getActivityTypeId());
	        activityType.setName(dto.getDescription());
	        return activityType;
	    }


    public ActivityTypeDTO addActivityType(ActivityTypeDTO activityTypeDTO) {
        ActivityType activityType = activityTypeDTOToEntity(activityTypeDTO);
        activityType = activityTypeRepository.save(activityType);
        return activityTypeEntityToDTO(activityType);
    }

    public List<ActivityTypeDTO> findAllActivityTypes() {
        return activityTypeRepository.findAll().stream()
                .map(this::activityTypeEntityToDTO)
                .collect(Collectors.toList());
    }


}
