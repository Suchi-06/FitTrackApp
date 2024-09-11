package com.fitnesstracker.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class ActivityTypeDTO {
	
	@Id
    private Long activityTypeId;
	
	@NotBlank(message = "Activity Description cannot be blank")
    private String description;

    
    public ActivityTypeDTO() {}

    public ActivityTypeDTO(Long activityTypeId, String description) {
        this.activityTypeId = activityTypeId;
        this.description = description;
    }

   
    public Long getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Long activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

