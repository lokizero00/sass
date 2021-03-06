package com.loki.sass.service.zone.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.RoleMapper;
import com.loki.sass.domain.mapper.ZoneMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.po.ZonePO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.zone.api.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    FeignRoleService feignRoleService;

    @Override
    public boolean addZone(ZoneRequestVO zoneRequestVO) throws BizException {
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
            zone.setCreateBy(zoneRequestVO.getCreateBy());
            zoneMapper.insertSelective(zone);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(ZoneResultCode.ZONE_ADD_ERROR);
        }
        return true;
    }

    @Override
    public boolean editZone(ZoneRequestVO zoneRequestVO) throws BizException {
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

        return true;
    }

    @Override
    public boolean deleteZone(Integer zoneId,Integer adminId) throws BizException {
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

        return true;
    }

    @Override
    public PageInfo<ZoneDTO> getZoneListSearch(ZoneQueryVO zoneQueryVO) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(zoneQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();


        if (!StringUtils.isEmpty(zoneQueryVO.getPage()) && !StringUtils.isEmpty(zoneQueryVO.getRows())) {
            PageHelper.startPage(zoneQueryVO.getPage(), zoneQueryVO.getRows());
        }
        List<ZonePO> list=new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                list=zoneMapper.selectByParam(admin.getZoneId(),zoneQueryVO.getName(),zoneQueryVO.getCreateByName(),zoneQueryVO.getUpdateByName(),zoneQueryVO.getState());
                break;
            case ZONE:
                list=zoneMapper.selectByParam(admin.getZoneId(),zoneQueryVO.getName(),zoneQueryVO.getCreateByName(),zoneQueryVO.getUpdateByName(),zoneQueryVO.getState());
                break;
            case ADMIN:
                list=zoneMapper.selectByParam(0,zoneQueryVO.getName(),zoneQueryVO.getCreateByName(),zoneQueryVO.getUpdateByName(),zoneQueryVO.getState());
                break;
            default:
                break;
        }

        PageInfo<ZonePO> zonePOPageInfo = new PageInfo<>(list);

        PageInfo<ZoneDTO> zoneDTOPageInfo = ConvertUtils.sourceToTarget(zonePOPageInfo, ZoneDTO.class);

        return zoneDTOPageInfo;
    }
}
