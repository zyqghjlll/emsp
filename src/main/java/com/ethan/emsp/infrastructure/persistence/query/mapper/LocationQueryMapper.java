package com.ethan.emsp.infrastructure.persistence.query.mapper;

import com.ethan.emsp.infrastructure.persistence.query.view.LocationView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationQueryMapper {
    LocationView getById(String locationId);
}
