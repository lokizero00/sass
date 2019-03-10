package com.loki.sass.service.zone.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.DoorResultCode;
import com.loki.sass.common.code.RegionResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.DoorDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.DoorQueryVO;
import com.loki.sass.common.vo.DoorRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.DoorMapper;
import com.loki.sass.domain.mapper.RegionMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.Door;
import com.loki.sass.domain.model.Region;
import com.loki.sass.domain.po.DoorPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.zone.api.DoorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@Service
@Transactional
public class DoorServiceImpl implements DoorService {
    @Autowired
    DoorMapper doorMapper;
    @Autowired
    RegionMapper regionMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    FeignRoleService feignRoleService;

    @Override
    public boolean addDoor(DoorRequestVO doorRequestVO) throws BizException {
        int nameCheck=doorMapper.checkName(doorRequestVO.getName(),doorRequestVO.getRegionId());
        if (nameCheck>0){
            throw new BizException(DoorResultCode.DOOR_NAME_EXISTS);
        }
        Region region=regionMapper.selectByPrimaryKey(doorRequestVO.getRegionId());
        if(null==region){
            throw new BizException(RegionResultCode.REGION_NOT_EXISTS);
        }

        try{
            Door door=new Door();
            door.setCode(doorRequestVO.getCode());
            door.setName(doorRequestVO.getName());
            door.setRegionId(region.getId());
            door.setRemoteIp(doorRequestVO.getRemoteIp());
            door.setCreateBy(doorRequestVO.getCreateBy());
            door.setCreateTime(new Date());
            door.setState(doorRequestVO.getState());
            doorMapper.insertSelective(door);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(DoorResultCode.DOOR_ADD_ERROR);
        }
        return true;
    }

    @Override
    public boolean editDoor(DoorRequestVO doorRequestVO) throws BizException {
        Door door=doorMapper.selectByPrimaryKey(doorRequestVO.getId());
        if(null==door){
            throw new BizException(DoorResultCode.DOOR_NOT_EXISTS);
        }
        Region region=regionMapper.selectByPrimaryKey(doorRequestVO.getRegionId());
        if(null==region){
            throw new BizException(RegionResultCode.REGION_NOT_EXISTS);
        }

        try{
            door.setCode(doorRequestVO.getCode());
            door.setName(doorRequestVO.getName());
            door.setRegionId(region.getId());
            door.setRemoteIp(doorRequestVO.getRemoteIp());
            door.setUpdateBy(doorRequestVO.getUpdateBy());
            door.setUpdateTime(new Date());
            door.setState(doorRequestVO.getState());
            doorMapper.updateByPrimaryKeySelective(door);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(DoorResultCode.DOOR_EDIT_ERROR);
        }
        return true;
    }

    @Override
    public boolean deleteDoor(Integer doorId, Integer adminId) throws BizException {
        Door door=doorMapper.selectByPrimaryKey(doorId);
        if(null==door){
            throw new BizException(DoorResultCode.DOOR_NOT_EXISTS);
        }

        try{
            door.setIsDeleted(1);
            door.setUpdateTime(new Date());
            door.setUpdateBy(adminId);
            doorMapper.updateByPrimaryKeySelective(door);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(DoorResultCode.DOOR_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public PageInfo<DoorDTO> getDoorListSearch(DoorQueryVO doorQueryVO) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(doorQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();


        if (!StringUtils.isEmpty(doorQueryVO.getPage()) && !StringUtils.isEmpty(doorQueryVO.getRows())) {
            PageHelper.startPage(doorQueryVO.getPage(), doorQueryVO.getRows());
        }
        List<DoorPO> list=new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                list=doorMapper.selectByParam(doorQueryVO.getName(),doorQueryVO.getCode(),doorQueryVO.getRemoteIp(),doorQueryVO.getRegionName(),doorQueryVO.getRegionId(),doorQueryVO.getCreateByName(),doorQueryVO.getUpdateByName(),doorQueryVO.getState(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                list=doorMapper.selectByParam(doorQueryVO.getName(),doorQueryVO.getCode(),doorQueryVO.getRemoteIp(),doorQueryVO.getRegionName(),doorQueryVO.getRegionId(),doorQueryVO.getCreateByName(),doorQueryVO.getUpdateByName(),doorQueryVO.getState(),admin.getZoneId(),0);
                break;
            case ADMIN:
                list=doorMapper.selectByParam(doorQueryVO.getName(),doorQueryVO.getCode(),doorQueryVO.getRemoteIp(),doorQueryVO.getRegionName(),doorQueryVO.getRegionId(),doorQueryVO.getCreateByName(),doorQueryVO.getUpdateByName(),doorQueryVO.getState(),0,0);
                break;
            default:
                break;
        }

        List<DoorDTO> dtoList= ConvertUtils.sourceToTarget(list,DoorDTO.class);
        PageInfo<DoorDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
