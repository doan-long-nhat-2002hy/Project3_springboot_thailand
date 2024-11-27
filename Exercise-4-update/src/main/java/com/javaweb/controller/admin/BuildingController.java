package com.javaweb.controller.admin;



import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IBuildingService;
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

    @Autowired
    private IBuildingService buildingService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        //dđàu tieen chưa có giá trị gì, sau khi submit form thì có giá trị
        modelAndView.addObject("modelSearch", buildingSearchRequest);

        List<BuildingSearchResponse> res = buildingService.findAll(buildingSearchRequest);

//        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
//        buildingSearchResponse.setListResult(res);
//        buildingSearchResponse.setTotalItems(res.size());
        modelAndView.addObject("buildingList", res);
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
        modelAndView.addObject("buildingEdit", buildingService.findById(Id));
        modelAndView.addObject("districts", District.type());
        modelAndView.addObject("typecodes", TypeCode.type());

        return modelAndView;
    }
}
