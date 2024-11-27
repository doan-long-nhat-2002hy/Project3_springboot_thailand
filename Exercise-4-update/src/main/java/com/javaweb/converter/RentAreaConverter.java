package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    public RentAreaEntity toRentAreaEntity(BuildingDTO buildingDTO, Long val) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(buildingDTO.getId());
        RentAreaEntity res = new RentAreaEntity();
        res.setValue(val);
        res.setBuilding(buildingEntity);
        return res;
    }
}
