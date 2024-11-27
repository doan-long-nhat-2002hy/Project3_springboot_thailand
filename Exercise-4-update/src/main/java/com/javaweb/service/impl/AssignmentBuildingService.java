package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuilding {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public void deleteByBuildingsId(List<Long> ids) {
        assignmentBuildingRepository.deleteByBuildingEntityIdIn(ids);
    }

    @Override
    @Transactional
    public void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<AssignmentBuildingEntity> results = assignmentBuildingRepository.findByBuildingEntity(buildingEntity);
        System.out.println("Found entities: " + results.size());
        assignmentBuildingRepository.deleteByBuildingEntity(buildingEntity);
        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
        for(Long it : staffIds){
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuildings(buildingEntity);

            UserEntity userEntity = userRepository.findById(it).get();
            assignmentBuildingEntity.setUserEntity(userEntity);

            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
    }
}
