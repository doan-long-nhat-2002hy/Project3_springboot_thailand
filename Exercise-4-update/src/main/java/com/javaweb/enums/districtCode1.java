package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum districtCode1 {
    QUAN_1 ("Quận 1"),
    QUAN_2 ("Quận 2"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),
    ;

    private final String districtName;
    districtCode1(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new HashMap<>();
        for(districtCode1 item : districtCode1.values()){
            listType.put(item.toString() , item.districtName);
        }
        return listType;
    }
}
