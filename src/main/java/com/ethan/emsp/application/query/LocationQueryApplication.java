package com.ethan.emsp.application.query;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.infrastructure.persistence.query.LocationQueryRepository;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationQueryApplication {

    private final LocationQueryRepository locationQueryRepository;

    public PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto) {
        return locationQueryRepository.queryByLastUpdated(queryDto);
    }

    public LocationVo getById(String locationId) {
        return locationQueryRepository.findById(locationId);
    }
}
