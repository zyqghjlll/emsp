package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.core.ddd.AggregateRoot;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.core.result.exception.NotFoundException;

import java.util.*;

public class Location implements AggregateRoot<LocationId> {
    private final LocationId id;
    private LocationAttributes attributes;
    private int maxEquipmentCount;
    private final List<Equipment> equipmentList;

    public Location(LocationId id, LocationAttributes attributes) {
        this(id, attributes, new ArrayList<>());
    }

    public Location(LocationId id, LocationAttributes attributes, List<Equipment> equipmentList) {
        this.id = Objects.requireNonNull(id, "Location ID must not be null");
        this.attributes = Objects.requireNonNull(attributes, "Location attributes must not be null");
        this.equipmentList = equipmentList;
        maxEquipmentCount = equipmentList.size();
    }

    @Override
    public LocationId getId() {
        return this.id;
    }

    public LocationAttributes getAttributes() {
        return this.attributes;
    }

    public void addEquipment(Equipment equipment) {
        if (maxEquipmentCount >= equipmentList.size()) {
            throw new BusinessException(ResultCode.BUSINESS_LIMIT, "Max equipment count reached");
        }
        // 校验唯一性
        boolean exists = equipmentList.stream().anyMatch(e -> e.id().equals(equipment.id()));
        if (exists) {
            throw new BusinessException(ResultCode.CONFLICT, "Equipment with same ID already exists");
        }
        equipmentList.add(equipment);
        maxEquipmentCount += 1;
    }

    public void removeEquipment(Equipment equipment) {
        boolean exists = equipmentList.stream().anyMatch(e -> e.id().equals(equipment.id()));
        if (!exists) {
            throw new NotFoundException("Equipment not found");
        }
        equipmentList.remove(equipment);
        maxEquipmentCount -= 1;
    }

    public List<Equipment> getEquipmentList() {
        return new ArrayList<>(this.equipmentList); // 返回副本防止外部修改
    }

    public void updateAttributes(LocationAttributes attributes) {
        this.attributes = attributes;
    }
}
