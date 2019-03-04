package com.loki.sass.service.zone.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;
import com.loki.sass.domain.po.ZonePO;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.service.zone.api.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * created by lokizero00 on 2019-03-04
 */
@Slf4j
@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    ZoneMapper zoneMapper;

    @Override
    public boolean addZone(ZoneRequestVO zoneRequestVO) throws BizException {

        return false;
    }

    @Override
    public boolean editZone(ZoneRequestVO zoneRequestVO) throws BizException {
        return false;
    }

    @Override
    public boolean deleteZone(Integer zoneId) throws BizException {
        return false;
    }

    @Override
    public PageInfo<ZoneDTO> getZoneListSearch(ZoneQueryVO zoneQueryVO) throws BizException {
        if (!StringUtils.isEmpty(zoneQueryVO.getPage()) && !StringUtils.isEmpty(zoneQueryVO.getRows())) {
            PageHelper.startPage(zoneQueryVO.getPage(), zoneQueryVO.getRows());
        }
        List<ZonePO> list=zoneMapper.selectByParam(zoneQueryVO.getName(),zoneQueryVO.getCreateByName(),zoneQueryVO.getUpdateByName(),zoneQueryVO.getState());
        List<ZoneDTO> dtoList= ConvertUtils.sourceToTarget(list,ZoneDTO.class);
        PageInfo<ZoneDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
