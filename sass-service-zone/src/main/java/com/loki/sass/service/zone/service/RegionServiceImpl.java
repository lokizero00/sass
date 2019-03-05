package com.loki.sass.service.zone.service;

import com.loki.sass.common.code.PropertyResultCode;
import com.loki.sass.common.code.RegionResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.domain.mapper.PropertyMapper;
import com.loki.sass.domain.mapper.RegionMapper;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.domain.model.Property;
import com.loki.sass.domain.model.Region;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.po.RegionPO;
import com.loki.sass.service.zone.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    @Autowired
    ZoneMapper zoneMapper;

    @Autowired
    PropertyMapper propertyMapper;

    @Autowired
    RegionMapper regionMapper;

    @Override
    public boolean addRegion(RegionRequestVO regionRequestVO) throws BizException {
        Zone zone=zoneMapper.selectByPrimaryKey(regionRequestVO.getZoneId());
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }
        Property property=propertyMapper.selectByPrimaryKey(regionRequestVO.getPropertyId());
        if(null==property){
            throw new BizException(PropertyResultCode.PROPERTY_NOT_EXISTS);
        }
        if(regionRequestVO.getParentId()>0){
            Region regionParent=regionMapper.selectByPrimaryKey(regionRequestVO.getParentId());
            if(null==regionParent){
                throw new BizException(RegionResultCode.REGION_PARENT_NOT_EXISTS);
            }
        }

        try{
            Region region=new Region();
            region.setName(regionRequestVO.getName());
            region.setDescription(regionRequestVO.getDescription());
            region.setZoneId(zone.getId());
            region.setPropertyId(property.getId());
            region.setType(regionRequestVO.getType());
            region.setFloor(regionRequestVO.getFloor());
            region.setParentId(regionRequestVO.getParentId());
            region.setCreateBy(regionRequestVO.getCreateBy());
            region.setCreateTime(new Date());
            regionMapper.insertSelective(region);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(RegionResultCode.REGION_ADD_ERROR);
        }
        return true;
    }

    @Override
    public boolean editRegion(RegionRequestVO regionRequestVO) throws BizException {
        Region region=regionMapper.selectByPrimaryKey(regionRequestVO.getId());
        if(null==region){
            throw new BizException(RegionResultCode.REGION_NOT_EXISTS);
        }
        Zone zone=zoneMapper.selectByPrimaryKey(regionRequestVO.getZoneId());
        if(null==zone){
            throw new BizException(ZoneResultCode.ZONE_NOT_EXISTS);
        }
        Property property=propertyMapper.selectByPrimaryKey(regionRequestVO.getPropertyId());
        if(null==property){
            throw new BizException(PropertyResultCode.PROPERTY_NOT_EXISTS);
        }
        if(regionRequestVO.getParentId()>0){
            Region regionParent=regionMapper.selectByPrimaryKey(regionRequestVO.getParentId());
            if(null==regionParent){
                throw new BizException(RegionResultCode.REGION_PARENT_NOT_EXISTS);
            }
        }

        try{
            region.setName(regionRequestVO.getName());
            region.setDescription(regionRequestVO.getDescription());
            region.setZoneId(zone.getId());
            region.setPropertyId(property.getId());
            region.setType(regionRequestVO.getType());
            region.setFloor(regionRequestVO.getFloor());
            region.setParentId(regionRequestVO.getParentId());
            region.setUpdateBy(regionRequestVO.getUpdateBy());
            region.setUpdateTime(new Date());
            regionMapper.updateByPrimaryKeySelective(region);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(RegionResultCode.REGION_EDIT_ERROR);
        }

        return true;
    }

    @Override
    public boolean deleteRegion(Integer regionId, Integer adminId) throws BizException {
        Region region=regionMapper.selectByPrimaryKey(regionId);
        if(null==region){
            throw new BizException(RegionResultCode.REGION_NOT_EXISTS);
        }

        try{
            region.setIsDeleted(1);
            region.setUpdateTime(new Date());
            region.setUpdateBy(adminId);
            regionMapper.updateByPrimaryKeySelective(region);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException(RegionResultCode.REGION_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public List<RegionDTO> getRegionListByParentId(Integer regionId) throws BizException {
        List<RegionPO> list=regionMapper.selectByParentId(regionId);
        List<RegionDTO> dtoList= ConvertUtils.sourceToTarget(list,RegionDTO.class);
        return dtoList;
    }

    @Override
    public List<RegionDTO> getRootRegionListByZoneId(Integer zoneId) throws BizException {
        List<RegionPO> list=regionMapper.selectByZoneId(zoneId);
        List<RegionDTO> dtoList= ConvertUtils.sourceToTarget(list,RegionDTO.class);
        return dtoList;
    }

    @Override
    public List<RegionDTO> getRootRegionListByPropertyId(Integer propertyId) throws BizException {
        List<RegionPO> list=regionMapper.selectByPropertyId(propertyId);
        List<RegionDTO> dtoList= ConvertUtils.sourceToTarget(list,RegionDTO.class);
        return dtoList;
    }


}
