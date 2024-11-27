package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaService rentAreaService;

    // lay nhan vien len
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        // tìm tòa nhà
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        // laasy tất cả nhân viên
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // duyệt danh sách
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if (staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }else{
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        //List<String> typeCode = buildingSearchRequest.getTypeCode();
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);

        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingSearchResponse> res = new ArrayList<>();

        for(BuildingEntity item: buildingEntities){
            BuildingSearchResponse building = buildingConverter.toBuildingSearchResponse(item);
            res.add(building);
        }
        return res;
    }

    @Override
    public int countTotalItem(List<BuildingSearchResponse> res) {
        return res.size();
    }

    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
//        if(!checkAddBuilding(buildingDTO)){
//            return null;
//        }
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));
//        if (buildingId != null){
//            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
//                    .orElseThrow(() -> new RuntimeException("Building not found"));
//        }
        buildingRepository.save(buildingEntity);
        buildingDTO.setId(buildingEntity.getId());
        if (StringUtils.check(buildingDTO.getRentArea())) rentAreaService.addRentArea(buildingDTO);
        return buildingDTO;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingDTO buildingDTO = buildingRepository.findById(id)
                .map(item -> {
                    BuildingDTO dto = modelMapper.map(item, BuildingDTO.class);

                    // Lấy danh sách RentAreaEntity từ BuildingEntity
                    String rentAreas = item.getRentAreaEntities().stream()
                            .map(rentArea -> rentArea.getValue().toString()) // Lấy giá trị value của RentAreaEntity
                            .collect(Collectors.joining(",")); // Nối các giá trị thành chuỗi "10,20,30"

                    dto.setRentArea(rentAreas);


                    return dto;
                })
                .orElse(null);
        return buildingDTO;
    }

    @Override
    public void deleteBuilding(List<Long> ids) {
        for(Long id: ids){
            assignmentBuildingRepository.deleteByBuildingEntity(buildingRepository.findById(id).get());
            buildingRepository.deleteById(id);
        }
    }


    public static String removeAccent(List<String> typeCodes){
        String s = String.join(",", typeCodes);
        return s;
    }

    public static boolean checkAddBuilding(BuildingDTO buildingDTO){
        if(!StringUtils.check(buildingDTO.getName())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getWard())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getStreet())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getDistrict())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getManagerName())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getManagerPhone())){
            return false;
        }
        if(!StringUtils.check(buildingDTO.getRentArea())){
            return false;
        }
        return true;
    }
}
