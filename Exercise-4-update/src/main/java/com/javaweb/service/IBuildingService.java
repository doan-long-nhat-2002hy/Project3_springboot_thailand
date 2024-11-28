package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBuildingService {
    ResponseDTO listStaffs(Long buildingId);

    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    int countTotalItem(List<BuildingSearchResponse> res);

    BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);

    BuildingDTO findById(Long id);

    void deleteBuilding(List<Long> ids);
}
