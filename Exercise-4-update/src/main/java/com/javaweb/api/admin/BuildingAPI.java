package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IAssignmentBuilding;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IRentArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IAssignmentBuilding assignmentBuilding;

    @Autowired
    private IRentArea iRentArea;

    @PostMapping
    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        BuildingDTO result = buildingService.addOrUpdateBuilding(buildingDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        // xuong db đễ xóa building theo danh sách id gửi về
        buildingService.deleteBuilding(ids);
        System.out.println("ok");
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        assignmentBuilding.addAssignmentBuildingEntity(assignmentBuildingDTO);
        System.out.println("ok");
    }
}
