package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IRentArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentAreaService implements IRentArea {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public void deleteByBuildings(Long[] ids) {
        for (Long it : ids) {
            BuildingEntity buildingEntity = buildingRepository.findById(Long.valueOf(it)).get();
            rentAreaRepository.deleteByBuildingId(buildingEntity.getId());
        }
    }

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
        rentAreaRepository.deleteByBuildingId(buildingEntity.getId());
//        rentAreaRepository.deleteByBuildingId(buildingEntity);

        String[] rentAreas = buildingDTO.getRentArea().split(",");

        for (String val: rentAreas){
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.valueOf(val));
            rentAreaRepository.save(rentAreaEntity);
        }
    }
}
