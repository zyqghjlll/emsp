package com.ethan.emsp.infrastructure.persistence.query;

import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationView;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationUpdatedQueryRepository {
    PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto);

    LocationView getById(String locationId);
}
