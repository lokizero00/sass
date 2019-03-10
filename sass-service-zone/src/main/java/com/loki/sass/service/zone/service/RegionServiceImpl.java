package com.loki.sass.service.zone.service;

import com.loki.sass.common.code.*;
import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.PropertyMapper;
import com.loki.sass.domain.mapper.RegionMapper;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.Property;
import com.loki.sass.domain.model.Region;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.po.RegionPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.zone.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    FeignRoleService feignRoleService;

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
    public List<RegionDTO> getRootRegionList(Integer adminId) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(adminId);
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        List<RegionPO> list=new ArrayList<>();

        switch(roleType){
            case PROPERTY:
                list=regionMapper.selectRootByPropertyId(admin.getPropertyId());
                break;
            case ZONE:
                list=regionMapper.selectRootByZoneId(admin.getZoneId());
                break;
            case ADMIN:
                list=regionMapper.selectRootAll();
                break;
            default:
                break;
        }

        List<RegionDTO> dtoList= ConvertUtils.sourceToTarget(list,RegionDTO.class);
        return dtoList;
    }
}
