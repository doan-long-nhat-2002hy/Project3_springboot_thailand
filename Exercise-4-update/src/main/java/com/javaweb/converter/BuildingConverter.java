package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        List<RentAreaEntity> rentAreaEntities = item.getRentAreaEntities();

        String rentArea = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        building.setRentArea(rentArea);

        Map<String, String> districts = District.type();

        String districtName = "";
        if(item.getDistrict() != null && item.getDistrict() != ""){
            districtName = districts.get(item.getDistrict());
        }

        if(districtName != null && districtName != ""){
            building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtName);
        }
        return building;
    }

    public BuildingEntity toEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        return buildingEntity;
    }
}
