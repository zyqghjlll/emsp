package com.ethan.emsp.domain.location;

import com.ethan.emsp.infrastructure.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationDomainService {
    @Autowired
    private IdGenerator idGenerator;
    public Location create(CreateLocationCmd command) {
        LocationAttributes attributes = new LocationAttributes();
        attributes.setName(command.getName());
        attributes.setAddress(command.getAddress());
        attributes.setCoordinates(command.getCoordinates());
        attributes.setBusinessHours(command.getBusinessHours());
        return new Location(idGenerator.generate(), attributes);
    }

//    public void updateBasicInfo(String name, String address, String coordinates, String businessHours) {
//        if (name != null) this.name = name;
//        if (address != null) this.address = address;
//        if (coordinates != null) this.coordinates = coordinates;
//        if (businessHours != null) this.businessHours = businessHours;
//    }
}
