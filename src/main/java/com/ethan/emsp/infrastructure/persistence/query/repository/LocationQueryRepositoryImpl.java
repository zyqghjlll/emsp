package com.ethan.emsp.infrastructure.persistence.query.repository;

import cn.hutool.core.collection.CollUtil;
import com.ethan.emsp.api.controller.dto.LocationQueryDto;
import com.ethan.emsp.api.controller.vo.ConnectorVo;
import com.ethan.emsp.api.controller.vo.EvsePageVo;
import com.ethan.emsp.api.controller.vo.LocationPageVo;
import com.ethan.emsp.api.controller.vo.LocationVo;
import com.ethan.emsp.infrastructure.persistence.event.po.ConnectorViewPO;
import com.ethan.emsp.infrastructure.persistence.event.po.EvseViewPO;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;
import com.ethan.emsp.infrastructure.persistence.query.LocationQueryRepository;
import com.ethan.emsp.infrastructure.persistence.query.common.PageResult;
import com.ethan.emsp.infrastructure.persistence.query.mapper.ConnectorQueryMapper;
import com.ethan.emsp.infrastructure.persistence.query.mapper.EvseQueryMapper;
import com.ethan.emsp.infrastructure.persistence.query.mapper.LocationQueryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class LocationQueryRepositoryImpl implements LocationQueryRepository {

    private LocationQueryMapper locationQueryMapper;
    private EvseQueryMapper evseQueryMapper;
    private ConnectorQueryMapper connectorQueryMapper;

    @Override
    public PageResult<LocationPageVo> queryByLastUpdated(LocationQueryDto queryDto) {
        // 1. 分页查主表
        PageHelper.startPage(queryDto.getPageNum(), queryDto.getPageSize());
        List<LocationViewPO> locationList = locationQueryMapper.selectByLastUpdated(queryDto);
        PageInfo<LocationViewPO> pageInfo = new PageInfo<>(locationList);

        if (CollUtil.isEmpty(locationList)) {
            return PageResult.of(new ArrayList<>(), pageInfo);
        }

        // 2. 查 evse 表
        List<String> locationIds = locationList.stream()
                .map(LocationViewPO::getLocationId)
                .distinct()
                .toList();
        List<EvseViewPO> evseList = evseQueryMapper.selectByLocationIds(locationIds);

        // 3. 查 connector 表
        List<String> evseIds = evseList.stream()
                .map(EvseViewPO::getEvseId)
                .distinct()
                .toList();
        List<ConnectorViewPO> connectorList = CollUtil.isEmpty(evseIds)
                ? Collections.emptyList()
                : connectorQueryMapper.selectByEvseIds(evseIds);

        // 4. 转换为结构化结果
        Map<String, List<EvsePageVo>> locationToEvseMap = evseList.stream()
                .collect(Collectors.groupingBy(
                        EvseViewPO::getLocationId,
                        Collectors.mapping(evse -> {
                            List<ConnectorVo> connectorVos = connectorList.stream()
                                    .filter(c -> Objects.equals(c.getEvseId(), evse.getEvseId()))
                                    .map(m -> new ConnectorVo(
                                            m.getConnectorType(),
                                            m.getConnectorVoltage(),
                                            m.getConnectorAmperage(),
                                            m.getConnectorPower(),
                                            m.getConnectorStandard()))
                                    .toList();
                            return new EvsePageVo(evse.getEvseId(), evse.getEvseStatus(), connectorVos);
                        }, Collectors.toList())
                ));

        // 5. 封装 LocationPageVo
        List<LocationPageVo> voList = locationList.stream().map(loc -> {
            LocationPageVo vo = new LocationPageVo();
            vo.setId(loc.getLocationId());
            vo.setName(loc.getName());
            vo.setAddress(loc.getAddress());
            vo.setLatitude(loc.getLatitude().toString());
            vo.setLongitude(loc.getLongitude().toString());
            vo.setOpenTime(loc.getOpenTime());
            vo.setCloseTime(loc.getCloseTime());
            vo.setEvses(locationToEvseMap.getOrDefault(loc.getLocationId(), List.of()));
            return vo;
        }).toList();

        return PageResult.of(voList, pageInfo);
    }


    @Override
    public LocationVo findById(String locationId) {
        return null;
    }
}
