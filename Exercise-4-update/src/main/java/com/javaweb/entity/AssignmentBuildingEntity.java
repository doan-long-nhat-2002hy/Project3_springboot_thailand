package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingEntity;


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BuildingEntity getBuildings() {
        return buildingEntity;
    }

    public void setBuildings(BuildingEntity buildings) {
        this.buildingEntity = buildings;
    }
}
