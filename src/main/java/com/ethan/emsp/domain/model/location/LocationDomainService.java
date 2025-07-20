package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.application.cmd.CreateLocationCmd;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.infrastructure.utils.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LocationDomainService {
    private final IdGenerator idGenerator;
    private final LocationCmdRepository locationRepository;

    public Location create(CreateLocationCmd command) {
        if (locationRepository.existsByNameAndAddress(command.name(), command.address())) {
            throw new BusinessException(ResultCode.CONFLICT, "同地址已存在该名称的充电站");
        }
        LocationAttributes attributes = LocationAttributes.builder()
                .name(command.name())
                .address(command.address())
                .coordinate(command.coordinate())
                .businessHours(command.businessHours())
                .build();
        return new Location(LocationId.of(idGenerator.generate()), attributes);
    }
}
