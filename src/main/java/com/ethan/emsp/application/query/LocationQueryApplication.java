package com.ethan.emsp.application.query;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.core.result.exception.NotFoundException;
import com.ethan.emsp.infrastructure.persistence.query.LocationQueryRepository;
import com.ethan.emsp.infrastructure.persistence.query.bo.LocationQueryBO;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationQueryApplication {

    private final LocationQueryRepository locationQueryRepository;

    public PageResult<LocationPageVo> listLocations(LocationQueryDto queryDto) {
        return locationQueryRepository.listLocations(queryDto);
    }

    public LocationVo getLocationById(String locationId) {
        LocationQueryBO byId = locationQueryRepository.findById(locationId);
        if (byId == null) {
            throw new NotFoundException();
        }
        return LocationVo.builder()
                .id(locationId)
                .name(byId.getName())
                .address(byId.getAddress())
                .latitude(byId.getLatitude().toString())
                .longitude(byId.getLongitude().toString())
                .openTime(byId.getOpenTime())
                .closeTime(byId.getCloseTime())
                .createdAt(byId.getCreatedAt())
                .updatedAt(byId.getUpdatedAt())
                .build();
    }
}
