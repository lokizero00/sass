package com.loki.sass.service.zone.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.PropertyResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.enums.PropertyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.PropertyQueryVO;
import com.loki.sass.common.vo.PropertyRequestVO;
import com.loki.sass.domain.mapper.PropertyMapper;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.domain.model.Property;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.po.PropertyPO;
import com.loki.sass.service.zone.api.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    ZoneMapper zoneMapper;

    @Override
    public boolean addProperty(PropertyRequestVO propertyRequestVO) throws BizException {
        int nameCheck=propertyMapper.checkName(propertyRequestVO.getName(),propertyRequestVO.getZoneId());
        if (nameCheck>0){
            throw new BizException(PropertyResultCode.PROPERTY_NAME_EXISTS);
        }

        Zone zone=zoneMapper.selectByPrimaryKey(propertyRequestVO.getZoneId());
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }

        try{
            Property property=new Property();
            property.setName(propertyRequestVO.getName());
            property.setManagerName(propertyRequestVO.getManagerName());
            property.setManagerPhone(propertyRequestVO.getManagerPhone());
            property.setCreateBy(propertyRequestVO.getCreateBy());
            property.setCreateTime(new Date());
            property.setState(PropertyState.USING.getValue());
            property.setZoneId(zone.getId());
            propertyMapper.insertSelective(property);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(PropertyResultCode.PROPERTY_ADD_ERROR);
        }
        return true;
    }

    @Override
    public boolean editProperty(PropertyRequestVO propertyRequestVO) throws BizException {
        Property property=propertyMapper.selectByPrimaryKey(propertyRequestVO.getId());
        if(null==property){
            throw new BizException(PropertyResultCode.PROPERTY_NOT_EXISTS);
        }

        Zone zone=zoneMapper.selectByPrimaryKey(propertyRequestVO.getZoneId());
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }

        try{
            property.setName(propertyRequestVO.getName());
            property.setManagerName(propertyRequestVO.getManagerName());
            property.setManagerPhone(propertyRequestVO.getManagerPhone());
            property.setState(PropertyState.USING.getValue());
            property.setZoneId(zone.getId());
            property.setUpdateBy(propertyRequestVO.getUpdateBy());
            property.setUpdateTime(new Date());
            propertyMapper.updateByPrimaryKeySelective(property);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(PropertyResultCode.PROPERTY_EDIT_ERROR);
        }
        return true;
    }

    @Override
    public boolean deleteProperty(Integer propertyId, Integer adminId) throws BizException {
        Property property=propertyMapper.selectByPrimaryKey(propertyId);
        if(null==property){
            throw new BizException(PropertyResultCode.PROPERTY_NOT_EXISTS);
        }

        try{
            property.setIsDeleted(1);
            property.setUpdateTime(new Date());
            property.setUpdateBy(adminId);
            propertyMapper.updateByPrimaryKeySelective(property);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(PropertyResultCode.PROPERTY_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public PageInfo<PropertyDTO> getPropertyListSearch(PropertyQueryVO propertyQueryVO) throws BizException {
        if (!StringUtils.isEmpty(propertyQueryVO.getPage()) && !StringUtils.isEmpty(propertyQueryVO.getRows())) {
            PageHelper.startPage(propertyQueryVO.getPage(), propertyQueryVO.getRows());
        }

        List<PropertyPO> list=propertyMapper.selectByParam(propertyQueryVO.getName(),propertyQueryVO.getCreateByName(),propertyQueryVO.getUpdateByName(),propertyQueryVO.getZoneName(),propertyQueryVO.getZoneId(),propertyQueryVO.getState());
        List<PropertyDTO> dtoList= ConvertUtils.sourceToTarget(list,PropertyDTO.class);
        PageInfo<PropertyDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
