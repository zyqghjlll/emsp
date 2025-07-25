package com.ethan.emsp.infrastructure.persistence.query;

import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.infrastructure.persistence.query.bo.LocationQueryBO;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationQueryRepository {
    PageResult<LocationPageVo> listLocations(LocationQueryDto queryDto);

    LocationQueryBO findById(String locationId);
}
