package com.loki.sass.service.resident.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserDetailDTO;
import com.loki.sass.common.dto.UserDoorDTO;
import com.loki.sass.common.dto.UserRegionDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.UserDetailQueryVO;
import com.loki.sass.common.vo.UserDoorQueryVO;
import com.loki.sass.common.vo.UserRegionQueryVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.UserMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.po.UserDetailPO;
import com.loki.sass.domain.po.UserDoorPO;
import com.loki.sass.domain.po.UserRegionPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.resident.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private FeignRoleService feignRoleService;

    @Override
    public PageInfo<UserDetailDTO> findUserDetailByPage(UserDetailQueryVO userDetailQueryVO) throws BizException{

        Admin admin=adminMapper.selectByPrimaryKey(userDetailQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();


        if (!StringUtils.isEmpty(userDetailQueryVO.getPage()) && !StringUtils.isEmpty(userDetailQueryVO.getRows())) {
            PageHelper.startPage(userDetailQueryVO.getPage(), userDetailQueryVO.getRows());
        }

        List<UserDetailPO> userDetailPOList = new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                userDetailPOList=userMapper.findUserDetailByParam(userDetailQueryVO.getMobile(),userDetailQueryVO.getNickName(),userDetailQueryVO.getRealName(),userDetailQueryVO.getState(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                userDetailPOList=userMapper.findUserDetailByParam(userDetailQueryVO.getMobile(),userDetailQueryVO.getNickName(),userDetailQueryVO.getRealName(),userDetailQueryVO.getState(),admin.getZoneId(),0);
                break;
            case ADMIN:
                userDetailPOList=userMapper.findUserDetailByParam(userDetailQueryVO.getMobile(),userDetailQueryVO.getNickName(),userDetailQueryVO.getRealName(),userDetailQueryVO.getState(),0,0);
                break;
            default:
                break;
        }
        List<UserDetailDTO> dtoList= ConvertUtils.sourceToTarget(userDetailPOList,UserDetailDTO.class);
        PageInfo<UserDetailDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public PageInfo<UserDoorDTO> findUserDoorByPage(UserDoorQueryVO userDoorQueryVO) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(userDoorQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        if (!StringUtils.isEmpty(userDoorQueryVO.getPage()) && !StringUtils.isEmpty(userDoorQueryVO.getRows())) {
            PageHelper.startPage(userDoorQueryVO.getPage(), userDoorQueryVO.getRows());
        }

        List<UserDoorPO> userDoorPOList = new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                userDoorPOList=userMapper.findUserDoorByParam(userDoorQueryVO.getDoorName(),userDoorQueryVO.getUserMobile(),userDoorQueryVO.getUserName(),userDoorQueryVO.getIsPermanent(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                userDoorPOList=userMapper.findUserDoorByParam(userDoorQueryVO.getDoorName(),userDoorQueryVO.getUserMobile(),userDoorQueryVO.getUserName(),userDoorQueryVO.getIsPermanent(),admin.getZoneId(),0);
                break;
            case ADMIN:
                userDoorPOList=userMapper.findUserDoorByParam(userDoorQueryVO.getDoorName(),userDoorQueryVO.getUserMobile(),userDoorQueryVO.getUserName(),userDoorQueryVO.getIsPermanent(),0,0);
                break;
            default:
                break;
        }
        List<UserDoorDTO> dtoList= ConvertUtils.sourceToTarget(userDoorPOList,UserDoorDTO.class);
        PageInfo<UserDoorDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public PageInfo<UserRegionDTO> findUserRegionByPage(UserRegionQueryVO userRegionQueryVO) throws BizException {

        Admin admin=adminMapper.selectByPrimaryKey(userRegionQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        if (!StringUtils.isEmpty(userRegionQueryVO.getPage()) && !StringUtils.isEmpty(userRegionQueryVO.getRows())) {
            PageHelper.startPage(userRegionQueryVO.getPage(), userRegionQueryVO.getRows());
        }

        List<UserRegionPO> userRegionPOList = new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                userRegionPOList=userMapper.findUserRegionByParam(userRegionQueryVO.getRegionName(),userRegionQueryVO.getUserMobile(),userRegionQueryVO.getUserName(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                userRegionPOList=userMapper.findUserRegionByParam(userRegionQueryVO.getRegionName(),userRegionQueryVO.getUserMobile(),userRegionQueryVO.getUserName(),admin.getZoneId(),0);
                break;
            case ADMIN:
                userRegionPOList=userMapper.findUserRegionByParam(userRegionQueryVO.getRegionName(),userRegionQueryVO.getUserMobile(),userRegionQueryVO.getUserName(),0,0);
                break;
            default:
                break;
        }
        List<UserRegionDTO> dtoList= ConvertUtils.sourceToTarget(userRegionPOList,UserRegionDTO.class);
        PageInfo<UserRegionDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
