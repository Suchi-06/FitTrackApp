package com.fitnesstracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.ActivityType;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType,Long> {

}
