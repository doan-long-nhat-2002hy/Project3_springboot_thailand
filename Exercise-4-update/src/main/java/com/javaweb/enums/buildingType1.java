package com.javaweb.enums;


import java.util.*;

public enum buildingType1 {
    TANG_TRET ("Tầng Trệt "),
    NGUYEN_CAN ("Nguyên Căn "),
    NOI_THAT ("Nội Thất ");

    private final String name;

    buildingType1(String name) {
        this.name = name;
    }

    public String getCode() {
        return name;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(buildingType1 item : buildingType1.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
