package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.infrastructure.persistence.query.common.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationQueryDto extends PageQuery {
    private LocalDateTime lastUpdated;
}
