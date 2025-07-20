package com.ethan.emsp.infrastructure.persistence.query;

import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationQueryRepository {
    PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto);

    LocationVo findById(String locationId);
}
