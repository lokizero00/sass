package com.loki.sass.service.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.PermissionResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.RolePermissionRequestVO;
import com.loki.sass.common.vo.RoleQueryVO;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.PermissionMapper;
import com.loki.sass.domain.mapper.RoleMapper;
import com.loki.sass.domain.mapper.RolePermissionMapper;
import com.loki.sass.domain.model.*;
import com.loki.sass.domain.po.RolePO;
import com.loki.sass.service.manager.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<RoleDTO> selectByUserId(Integer adminId)throws BizException {
        List<Role> roleList=roleMapper.selectByAdminId(adminId);
        List<RoleDTO> roleDTOList= ConvertUtils.sourceToTarget(roleList,RoleDTO.class);
        return roleDTOList;
    }

    @Override
    public Boolean insert(RoleRequestVO roleRequestVO)throws BizException {
        if(adminMapper.count(roleRequestVO.getCreateBy())==0){
           throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int count = roleMapper.checkRole(roleRequestVO.getRole());
        if(count>0){
            throw new BizException(RoleResultCode.ROLE_NAME_EXIST);
        }
        int result = 0;
        try{
            Role role = ConvertUtils.sourceToTarget(roleRequestVO, Role.class);
            role.setAvailable(1);
            role.setIsDeleted(0);
            result = roleMapper.insert(role);
        }catch(Exception e){
            throw new BizException(RoleResultCode.ROLE_ADD_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean update(RoleRequestVO roleRequestVO)throws BizException {
        if(adminMapper.count(roleRequestVO.getUpdateBy())==0){
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int result = 0;
        try{
            Role role = ConvertUtils.sourceToTarget(roleRequestVO, Role.class);
            result = roleMapper.updateByPrimaryKeySelective(role);
            log.info("[roleService更新记录],roleVO={},result={}",roleRequestVO,result);
        }catch(Exception e){
            throw new BizException(RoleResultCode.ROLE_UPDATE_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean deleteById(Integer id,Integer operatorId)throws BizException {
        if(adminMapper.count(operatorId)==0){
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role==null){
            throw new BizException(RoleResultCode.ROLE_NOT_EXIST);
        }
        int result = 0;
        try{
            role.setIsDeleted(1);
            role.setUpdateBy(operatorId);
            result = roleMapper.updateByPrimaryKeySelective(role);
            log.info("[roleService删除记录],id={}",id);
        }catch(Exception e){
            throw new BizException(RoleResultCode.ROLE_DELETE_ERROR);
        }
        return result>0;
    }

    @Override
    public List<RoleDTO> findAll()throws BizException {
        List<Role> roleList = roleMapper.selectByExample(new RoleExample());
        return ConvertUtils.sourceToTarget(roleList, RoleDTO.class);
    }

    @Override
    public PageInfo<RoleDTO> getRoleListSearch(RoleQueryVO roleQueryVO)throws BizException {
        if (!StringUtils.isEmpty(roleQueryVO.getPage()) && !StringUtils.isEmpty(roleQueryVO.getRows())) {
            PageHelper.startPage(roleQueryVO.getPage(), roleQueryVO.getRows());
        }

        Admin admin=adminMapper.selectByPrimaryKey(roleQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        SysRole roleType=this.getDataIsolationLevel(admin.getId());

        List<RolePO> list=new ArrayList<>();

        switch(roleType){
            case PROPERTY:
                break;
            case ZONE:
                list=roleMapper.selectByParam(roleQueryVO.getRole(),roleQueryVO.getCreateByName(),roleQueryVO.getUpdateByName(),admin.getZoneId());
                break;
            case ADMIN:
                list=roleMapper.selectByParam(roleQueryVO.getRole(),roleQueryVO.getCreateByName(),roleQueryVO.getUpdateByName(),0);
                break;
            default:
                break;
        }

        List<RoleDTO> dtoList= ConvertUtils.sourceToTarget(list,RoleDTO.class);
        return new PageInfo<>(dtoList);
    }

    @Override
    public RoleDTO findById(Integer id)throws BizException {
        Role role = roleMapper.selectByPrimaryKey(id);
        return ConvertUtils.sourceToTarget(role, RoleDTO.class);
    }

    @Override
    public List<PermissionDTO> findOwnPermissions(Integer roleId) throws BizException {
        int count = roleMapper.count(roleId);
        if(count==0){
            throw new BizException(RoleResultCode.ROLE_NOT_EXIST);
        }
        List<Permission> permissionList = permissionMapper.selectByRoleId(roleId);
        return ConvertUtils.sourceToTarget(permissionList,PermissionDTO.class);
    }

    @Override
    public Boolean updateOwnPermissions(RolePermissionRequestVO rolePermissionRequestVO)throws BizException{
        int roleCount = roleMapper.count(rolePermissionRequestVO.getRoleId());
        if(roleCount==0){
            throw new BizException(RoleResultCode.ROLE_NOT_EXIST);
        }
        //校验权限是否存在
        int permissionCount = permissionMapper.countForList(rolePermissionRequestVO.getPermissionIdsList());
        if(permissionCount!=rolePermissionRequestVO.getPermissionIdsList().size()){
            throw new BizException(PermissionResultCode.PERMISSION_NOT_All_EXIST);
        }
        int result = 0;
        try{
            rolePermissionMapper.deleteRoleOwnPermissions(rolePermissionRequestVO.getRoleId());
            RolePermissionRequest rolePermissionRequest = ConvertUtils.sourceToTarget(rolePermissionRequestVO, RolePermissionRequest.class);
            result = rolePermissionMapper.addRoleOwnPermissions(rolePermissionRequest);
        }catch(Exception e){
            throw new BizException(RoleResultCode.ROLE_PERMISSION_OPERATE_ERROR);
        }
        return result>0;
    }

    @Override
    public SysRole getDataIsolationLevel(Integer adminId) throws BizException {
        //数据隔离
        //roleType，PROPERTY：物业管理员，ZONE：小区管理员，ADMIN：超级管理员
        SysRole roleType=SysRole.PROPERTY;
        Admin admin=adminMapper.selectByPrimaryKey(adminId);
        List<String> roleList=roleMapper.selectRoleByAdminId(adminId);

        if(roleList.contains(SysRole.ZONE.getValue())){
            roleType=SysRole.ZONE;
        }

        if(roleList.contains(SysRole.ADMIN.getValue())){
            roleType=SysRole.ADMIN;
        }

        return roleType;
    }

}
