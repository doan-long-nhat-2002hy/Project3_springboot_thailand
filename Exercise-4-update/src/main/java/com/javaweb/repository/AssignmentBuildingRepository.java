package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    public void deleteByBuildingEntity(BuildingEntity buildingEntity);
    public List<AssignmentBuildingEntity> findByBuildingEntity(BuildingEntity buildingEntity);
    public void deleteByBuildingEntityIdIn(List<Long> ids);
}
