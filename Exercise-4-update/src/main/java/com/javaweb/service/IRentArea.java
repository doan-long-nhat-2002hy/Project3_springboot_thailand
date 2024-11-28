package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;

public interface IRentArea {
    void deleteByBuildings(Long[] ids);
    void addRentArea(BuildingDTO buildingDTO);
}
