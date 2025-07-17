package com.ethan.emsp.infrastructure.persistence.query.repository;

import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.infrastructure.persistence.query.LocationUpdatedQueryRepository;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.infrastructure.persistence.query.mapper.LocationQueryMapper;
import com.ethan.emsp.infrastructure.persistence.query.mapper.LocationUpdateDetailViewMapper;
import com.ethan.emsp.infrastructure.persistence.query.mapper.LocationUpdateMainViewMapper;
import com.ethan.emsp.infrastructure.persistence.query.view.LocationView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class LocationUpdatedQueryRepositoryImpl implements LocationUpdatedQueryRepository {

    private final LocationUpdateMainViewMapper locationUpdateMainViewMapper;
    private final LocationUpdateDetailViewMapper locationUpdateDetailViewMapper;
    private final LocationQueryMapper locationQueryMapper;

    @Override
    public PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto) {
//        PageHelper.startPage(queryDto.getPageNum(), queryDto.getPageSize());
//        List<LocationUpdateDetailViewPO> list = locationUpdateMainViewMapper.selectByLastUpdated(queryDto);
//        PageInfo<LocationUpdateDetailViewPO> pageInfo = new PageInfo<>(list);
//
//        Map<String, LocationPageVo> locationMap = new LinkedHashMap<>();
//
//        for (LocationUpdateDetailViewPO row : rows) {
//            LocationPageVo location = locationMap.computeIfAbsent(row.getLocationId(), id -> {
//                LocationPageVo vo = new LocationPageVo();
//                vo.setId(row.getLocationId());
//                vo.setName(row.getLocationName());
//                vo.setAddress(row.getLocationAddress());
//                vo.setLatitude(row.getLatitude());
//                vo.setLongitude(row.getLongitude());
//                vo.setOpenTime(row.getOpenTime());
//                vo.setCloseTime(row.getCloseTime());
//                return vo;
//            });
//
//            EvsePageVo evse = null;
//            if (row.getEvseId() != null) {
//                evse = location.getEvses().stream()
//                        .filter(e -> e.getId().equals(row.getEvseId()))
//                        .findFirst()
//                        .orElseGet(() -> {
//                            EvsePageVo eVo = new EvsePageVo();
//                            eVo.setId(row.getEvseId());
//                            eVo.setStatus(row.getEvseStatus());
//                            location.getEvses().add(eVo);
//                            return eVo;
//                        });
//            }
//
//            if (evse != null && row.getConnectorType() != null) {
//                evse.getConnectors().add(new ConnectorVo(
//                        row.getConnectorType(),
//                        row.getConnectorVoltage(),
//                        row.getConnectorAmperage(),
//                        row.getConnectorPower(),
//                        row.getConnectorStandard()
//                ));
//            }
//        }

        return null;
    }

    @Override
    public LocationView getById(String locationId) {
        return locationQueryMapper.getById(locationId);
    }
}
