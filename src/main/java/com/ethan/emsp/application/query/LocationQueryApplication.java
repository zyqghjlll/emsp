package com.ethan.emsp.application.query;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.infrastructure.persistence.query.LocationUpdatedQueryRepository;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationQueryApplication {

    private final LocationUpdatedQueryRepository locationUpdatedQueryRepository;

    public PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto) {
        return locationUpdatedQueryRepository.queryByLastUpdated(queryDto);
    }

    public LocationView getById(String locationId) {
        return locationUpdatedQueryRepository.getById(locationId);
    }
}
