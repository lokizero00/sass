package com.loki.sass.service.manager.service;

import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.PermissionResultCode;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.PermissionRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.PermissionMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.domain.model.PermissionExample;
import com.loki.sass.service.manager.api.PermissionService;
import com.loki.sass.service.manager.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    RoleService roleService;

    @Override
    public List<PermissionDTO> selectByRoleId(Integer roleId) throws BizException {
        List<Permission> permissionList = permissionMapper.selectByRoleId(roleId);
        List<PermissionDTO> _permissionDTOList = ConvertUtils.sourceToTarget(permissionList,PermissionDTO.class);
        List<PermissionDTO> permissionDTOList=new ArrayList<>();
        for (PermissionDTO permissionDTO : _permissionDTOList) {
            if (permissionDTO.getParentId() == 0) {
                if (checkIsExistChildNode(_permissionDTOList,permissionDTO.getId()) ){
                    permissionDTO.setChildList(getChildList(_permissionDTOList, permissionDTO.getId()));
                }
                permissionDTOList.add(permissionDTO);
            }
        }
        return permissionDTOList;
    }

    @Override
    public List<PermissionDTO> selectByRoleIds(List<Integer> roleIdList) throws BizException {
        List<Permission> permissionList = permissionMapper.selectByRoleIds(roleIdList);
        List<PermissionDTO> _permissionDTOList = ConvertUtils.sourceToTarget(permissionList,PermissionDTO.class);
        List<PermissionDTO> permissionDTOList=new ArrayList<>();
        for (PermissionDTO permissionDTO : _permissionDTOList) {
            if (permissionDTO.getParentId() == 0) {
                if (checkIsExistChildNode(_permissionDTOList,permissionDTO.getId()) ){
                    permissionDTO.setChildList(getChildList(_permissionDTOList, permissionDTO.getId()));
                }
                permissionDTOList.add(permissionDTO);
            }
        }
        return permissionDTOList;
    }

    @Override
    public List<PermissionDTO> selectButtonByRoleId(Integer roleId) throws BizException {
        List<Permission> permissionList = permissionMapper.selectButtonByRoleId(roleId);
        List<PermissionDTO> permissionDTOList = ConvertUtils.sourceToTarget(permissionList,PermissionDTO.class);
        return permissionDTOList;
    }

    /**
     * 判断当前节点存在子节点
     * @param permissionDTOList
     * @return
     *
     */
    private boolean checkIsExistChildNode(List<PermissionDTO> permissionDTOList,Integer pid) {
        for (PermissionDTO permissionDTO : permissionDTOList) {
            if (permissionDTO.getParentId().equals(pid)){
                return true;
            }
        }
        return false;
    }

    /**
     * 递归加载子节点
     * @param permissionDTOList
     * @param pid
     * @param
     * @return
     *
     */
    public List<PermissionDTO> getChildList(List<PermissionDTO> permissionDTOList, Integer pid) {
        List<PermissionDTO> childList=new ArrayList<>();
        for (PermissionDTO permissionDTO : permissionDTOList) {
            if (permissionDTO.getParentId().equals(pid)) {
                if (checkIsExistChildNode(permissionDTOList, permissionDTO.getId())) {
                    permissionDTO.setChildList(getChildList(permissionDTOList, permissionDTO.getId()));
                }
                childList.add(permissionDTO);
            }
        }
        return childList;
    }

    @Override
    public Boolean insert(PermissionRequestVO permissionRequestVO)throws BizException {
        if(adminMapper.count(permissionRequestVO.getCreateBy())==0){
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int count = permissionMapper.checkName(permissionRequestVO.getName());
        if(count>0){
            throw new BizException(PermissionResultCode.PERMISSION_NAME_EXIST);
        }
        int result = 0;
        try{
            Permission permission = ConvertUtils.sourceToTarget(permissionRequestVO, Permission.class);
            permission.setAvailable(1);
            permission.setCreateBy(permissionRequestVO.getCreateBy());
            permission.setIsDeleted(0);
            result = permissionMapper.insert(permission);
            log.info("[permissionService插入记录],permissionVO={},result={}",permissionRequestVO,result);
        }catch(Exception e){
            throw new BizException(PermissionResultCode.PERMISSION_ADD_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean delete(Integer which,Integer operatorId) throws BizException{
        if(adminMapper.count(operatorId)==0){
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int result = 0;
        Permission permission = permissionMapper.selectByPrimaryKey(which);
        if(permission==null){
            throw new BizException(PermissionResultCode.PERMISSION_NOT_EXIST);
        }
        try{
            log.info("[permissionService删除记录],id={}",which);
            permission.setIsDeleted(1);
            permission.setUpdateBy(operatorId);
            result = permissionMapper.updateByPrimaryKeySelective(permission);
        }catch(Exception e){
            throw new BizException(PermissionResultCode.PERMISSION_DELETE_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean update(PermissionRequestVO permissionRequestVO) throws BizException{
        if(adminMapper.count(permissionRequestVO.getUpdateBy())==0){
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int result = 0;
        try{
            Permission permission = ConvertUtils.sourceToTarget(permissionRequestVO, Permission.class);
            result = permissionMapper.updateByPrimaryKeySelective(permission);
            log.info("[permissionService更新记录],permissionVO={},result={}",permissionRequestVO,result);
        }catch(Exception e){
            throw new BizException(PermissionResultCode.PERMISSION_UPDATE_ERROR);
        }
        return result>0;
    }

    @Override
    public PermissionDTO findOne(Integer id) throws BizException{
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        log.info("[permissionService查询记录],id={}",id);
        PermissionDTO permissionDTO = ConvertUtils.sourceToTarget(permission, PermissionDTO.class);
        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> findAll() throws BizException{
        List<Permission> permissionList = permissionMapper.selectByExample(new PermissionExample());
        List<PermissionDTO> permissionDTOList = ConvertUtils.sourceToTarget(permissionList, PermissionDTO.class);
        return permissionDTOList;
    }

    @Override
    public List<PermissionDTO> findRootListByAdminId(Integer adminId) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(adminId);
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        SysRole roleType=roleService.getDataIsolationLevel(admin.getId());

        List<Permission> list=new ArrayList<>();

        switch(roleType){
            case PROPERTY:
                break;
            case ZONE:
                list=permissionMapper.selectRootList(1);
                break;
            case ADMIN:
                list=permissionMapper.selectRootList(0);
                break;
            default:
                break;
        }

        List<PermissionDTO> permissionDTOList = ConvertUtils.sourceToTarget(list, PermissionDTO.class);
        return permissionDTOList;
    }

    @Override
    public List<PermissionDTO> findListByParentId(Integer permissionId) throws BizException {
        List<Permission> permissionList = permissionMapper.selectListByParentId(permissionId);
        List<PermissionDTO> permissionDTOList = ConvertUtils.sourceToTarget(permissionList, PermissionDTO.class);
        return permissionDTOList;
    }

}
