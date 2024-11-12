package com.javaweb.controller.admin;



import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);
        // xuong db laay data
        List<BuildingSearchResponse> responseList = new ArrayList<>();
        BuildingSearchResponse item = new BuildingSearchResponse();
        item.setId(3L);
        item.setName("ACM Building");
        item.setAddress("130 quajng trujng");
        item.setNumberOfBasement(2L);
        item.setManagerName("Anh Long Nhat");
        item.setManagerPhone("0808079087");
        item.setRentArea("100,200,300");
        responseList.add(item);
        BuildingSearchResponse item1 = new BuildingSearchResponse();
        item1.setId(4L);
        item1.setName("AC Building");
        item1.setAddress("13 quang trung");
        item1.setNumberOfBasement(6L);
        item1.setManagerName("Anh Long");
        item1.setManagerPhone("0808079");
        item.setRentArea("100,200");
        responseList.add(item1);
        modelAndView.addObject("buildingList", responseList);
        modelAndView.addObject("listStaffs", userService.getStaffs());
        modelAndView.addObject("districts", District.type());
        modelAndView.addObject("typecodes", TypeCode.type());
        return modelAndView;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO ,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("districts", District.type());
        modelAndView.addObject("typecodes", TypeCode.type());
        return modelAndView;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long Id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        //zuong db tim building theo id
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(Id);
        buildingDTO.setName("asfd");
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("districts", District.type());
        modelAndView.addObject("typecodes", TypeCode.type());

        return modelAndView;
    }
}
