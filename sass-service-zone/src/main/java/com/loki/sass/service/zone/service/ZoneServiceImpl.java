package com.loki.sass.service.zone.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.enums.ZoneState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.po.ZonePO;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.service.zone.api.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
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
    public ResultDTO<?> addZone(ZoneRequestVO zoneRequestVO) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        int nameCheck=zoneMapper.checkName(zoneRequestVO.getName());
        if (nameCheck>0){
            throw new BizException(ZoneResultCode.ZONE_NAME_EXISTS);
        }

        try{
            Zone zone=new Zone();
            zone.setName(zoneRequestVO.getName());
            zone.setNation(zoneRequestVO.getNation());
            zone.setProvince(zoneRequestVO.getProvince());
            zone.setCity(zoneRequestVO.getCity());
            zone.setTown(zoneRequestVO.getTown());
            zone.setCreateTime(new Date());
            zone.setState(zoneRequestVO.getState());
            zoneMapper.insertSelective(zone);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(ZoneResultCode.ZONE_ADD_ERROR);
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDTO<?> editZone(ZoneRequestVO zoneRequestVO) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        Zone zone=zoneMapper.selectByPrimaryKey(zoneRequestVO.getId());
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }

        try{
            zone.setName(zoneRequestVO.getName());
            zone.setTown(zoneRequestVO.getTown());
            zone.setNation(zoneRequestVO.getNation());
            zone.setCity(zoneRequestVO.getCity());
            zone.setProvince(zoneRequestVO.getProvince());
            zone.setUpdateBy(zoneRequestVO.getUpdateBy());
            zone.setUpdateTime(new Date());
            zone.setState(zoneRequestVO.getState());
            zoneMapper.updateByPrimaryKeySelective(zone);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(ZoneResultCode.ZONE_EDIT_ERROR);
        }

        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDTO<?> deleteZone(Integer zoneId,Integer adminId) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        Zone zone=zoneMapper.selectByPrimaryKey(zoneId);
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }

        try{
            zone.setIsDeleted(1);
            zone.setUpdateBy(adminId);
            zone.setUpdateTime(new Date());
            zoneMapper.updateByPrimaryKeySelective(zone);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(ZoneResultCode.ZONE_DELETE_ERROR);
        }

        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultDTO<?> getZoneListSearch(ZoneQueryVO zoneQueryVO) throws BizException {
        ResultDTO<PageInfo<ZoneDTO>> result=new ResultDTO<>();
        if (!StringUtils.isEmpty(zoneQueryVO.getPage()) && !StringUtils.isEmpty(zoneQueryVO.getRows())) {
            PageHelper.startPage(zoneQueryVO.getPage(), zoneQueryVO.getRows());
        }
        List<ZonePO> list=zoneMapper.selectByParam(zoneQueryVO.getName(),zoneQueryVO.getCreateByName(),zoneQueryVO.getUpdateByName(),zoneQueryVO.getState());
        List<ZoneDTO> dtoList= ConvertUtils.sourceToTarget(list,ZoneDTO.class);
        PageInfo<ZoneDTO> pageInfo = new PageInfo<>(dtoList);
        result.setSuccess(true);
        result.setModule(pageInfo);
        return result;
    }
}
