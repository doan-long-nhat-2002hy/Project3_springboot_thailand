//package com.javaweb.repository.custom.impl;
//
//import com.javaweb.builder.BuildingSearchBuilder;
//import com.javaweb.entity.BuildingEntity;
//import com.javaweb.repository.custom.BuildingRepositoryCustom;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.lang.reflect.Field;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){
//        Long staffId = buildingSearchBuilder.getStaffId();
//        if(staffId != null){
//            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
//        }
//    }
//
//    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
//        try{
//            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
//            for(Field item: fields){
//                item.setAccessible(true);
//                String fieldName = item.getName();
//                if(!fieldName.equals("staffId") &&!fieldName.equals("typeCode") && !fieldName.equals("areaFrom") && !fieldName.equals("areaTo")
//                        && !fieldName.equals("rentPriceFrom") && !fieldName.equals("rentPriceTo")){
//                    Object value = item.get(buildingSearchBuilder);
//                    if (value != null){
//                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
//                            where.append(" AND b." + fieldName + " = " + value);
//                        }
//                        else if(item.getType().getName().equals("java.lang.String")){
//                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
//        Long staffId = buildingSearchBuilder.getStaffId();
//        if (staffId != null){
//            where.append(" AND assignmentbuilding.staffid = " + staffId);
//        }
//
//        Long areaFrom = buildingSearchBuilder.getAreaFrom();
//        Long areaTo = buildingSearchBuilder.getAreaTo();
//        if (areaFrom != null || areaTo != null){
//            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
//            if(areaFrom != null){
//                where.append(" AND r.value >= " + areaFrom);
//            }
//            if(areaTo != null){
//                where.append(" AND r.value <= " + areaTo);
//            }
//            where.append(") ");
//        }
//
//        Long rentpriceFrom = buildingSearchBuilder.getRentPriceFrom();
//        Long rentpriceTo = buildingSearchBuilder.getRentPriceTo();
//        if (rentpriceFrom != null || rentpriceTo != null) {
//            if (rentpriceFrom != null) {
//                where.append(" AND b.rentprice >= " + rentpriceFrom);
//            }
//            if (rentpriceTo != null) {
//                where.append(" AND b.rentprice <= " + rentpriceTo);
//            }
//        }
//
//        List<String> typeCode = buildingSearchBuilder.getTypeCode();
//        if(typeCode != null && typeCode.size()!= 0){
//            where.append(" AND ( ");
//            String sql = typeCode.stream().map(it -> " b.type like " + "'%" + it  + "%' ").collect(Collectors.joining(" or "));
//            where.append(sql + " ) ");
//        }
//    }
//
//
//    @Override
//    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
//        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
//        //b.id, b.name, b.street, b.ward, b.district, b.managername, b.managerphone, b.floorarea, b.rentprice, b.brokeragefee, b.servicefee
//        joinTable(buildingSearchBuilder, sql);
//
//        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
//        queryNomal(buildingSearchBuilder, where);
//        querySpecial(buildingSearchBuilder, where);
//        sql.append(where);
//        //sql.append(" GROUP BY b.id");
//        System.out.println(sql);
//
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        return query.getResultList();
//    }
//}
package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null){
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
    }

    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item: fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") &&!fieldName.equals("typeCode") && !fieldName.equals("areaFrom") && !fieldName.equals("areaTo")
                        && !fieldName.equals("rentPriceFrom") && !fieldName.equals("rentPriceTo")){
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null && (value instanceof String ? !((String) value).isEmpty() : true)){  // Kiểm tra chuỗi không rỗng
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else if(item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null){
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }

        Long areaFrom = buildingSearchBuilder.getAreaFrom();
        Long areaTo = buildingSearchBuilder.getAreaTo();
        if (areaFrom != null || areaTo != null){
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if(areaFrom != null){
                where.append(" AND r.value >= " + areaFrom);
            }
            if(areaTo != null){
                where.append(" AND r.value <= " + areaTo);
            }
            where.append(") ");
        }

        Long rentpriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentpriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentpriceFrom != null || rentpriceTo != null) {
            if (rentpriceFrom != null) {
                where.append(" AND b.rentprice >= " + rentpriceFrom);
            }
            if (rentpriceTo != null) {
                where.append(" AND b.rentprice <= " + rentpriceTo);
            }
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() != 0){
            where.append(" AND ( ");
            String sql = typeCode.stream().map(it -> " b.type like " + "'%" + it  + "%' ").collect(Collectors.joining(" or "));
            where.append(sql + " ) ");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        joinTable(buildingSearchBuilder, sql);

        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);

        System.out.println(sql);  // In câu SQL để kiểm tra

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public Object[] findBuildingById(Long id) {
        StringBuilder sql = new StringBuilder("SELECT b.*, GROUP_CONCAT(r.value) as rentArea FROM building b ");
        sql.append(" LEFT JOIN rentarea r ON b.id = r.buildingid ");
        sql.append(" WHERE b.id = ?1 ");
        sql.append(" GROUP BY b.id ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, id);
        return (Object[]) query.getSingleResult();
    }
}
