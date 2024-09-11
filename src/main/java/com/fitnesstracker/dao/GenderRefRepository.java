package com.fitnesstracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesstracker.entity.GenderRef;

@Repository
public interface GenderRefRepository extends JpaRepository<GenderRef,Long>{

}
