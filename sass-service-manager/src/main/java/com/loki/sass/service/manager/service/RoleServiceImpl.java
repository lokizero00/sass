package com.loki.sass.service.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.PermissionResultCode;
import com.loki.sass.common.code.RolePermissionResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.RolePermissionRequestVO;
import com.loki.sass.common.vo.RoleQueryVO;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.PermissionMapper;
import com.loki.sass.domain.mapper.RoleMapper;
import com.loki.sass.domain.mapper.RolePermissionMapper;
import com.loki.sass.domain.model.Role;
import com.loki.sass.domain.model.RoleExample;
import com.loki.sass.domain.model.RolePermission;
import com.loki.sass.domain.po.RolePO;
import com.loki.sass.service.manager.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        List<RoleDTO> roleDTOList = ConvertUtils.sourceToTarget(roleList, RoleDTO.class);
        return roleDTOList;
    }

    @Override
    public PageInfo<RoleDTO> getAdminListSearch(RoleQueryVO roleQueryVO)throws BizException {
        if (!StringUtils.isEmpty(roleQueryVO.getPage()) && !StringUtils.isEmpty(roleQueryVO.getRows())) {
            PageHelper.startPage(roleQueryVO.getPage(), roleQueryVO.getRows());
        }
        List<RolePO> list = roleMapper.selectByParam(roleQueryVO.getRole(),roleQueryVO.getCreateByName(),roleQueryVO.getUpdateByName());
        List<RoleDTO> dtoList= ConvertUtils.sourceToTarget(list,RoleDTO.class);
        PageInfo<RoleDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public RoleDTO findById(Integer id)throws BizException {
        Role role = roleMapper.selectByPrimaryKey(id);
        RoleDTO roleDTO = ConvertUtils.sourceToTarget(role, RoleDTO.class);
        return roleDTO;
    }

    public void checkExists(RolePermissionRequestVO rolePermissionRequestVO)throws BizException {
        int roleCount=0,permissionCount = 0;
        roleCount = roleMapper.count(rolePermissionRequestVO.getRoleId());
        if(roleCount==0){
            throw new BizException(RoleResultCode.ROLE_NOT_EXIST);
        }
        permissionCount = permissionMapper.count(rolePermissionRequestVO.getPermissionId());
        if(permissionCount==0){
            throw new BizException(PermissionResultCode.PERMISSION_NOT_EXIST);
        }
    }

    @Override
    public Boolean hasPermission(RolePermissionRequestVO rolePermissionRequestVO) throws BizException {
        checkExists(rolePermissionRequestVO);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(rolePermissionRequestVO.getRoleId());
        rolePermission.setPermissionId(rolePermissionRequestVO.getPermissionId());
        return rolePermissionMapper.count(rolePermission)>0;
    }

    @Override
    public Boolean addPermission(RolePermissionRequestVO rolePermissionRequestVO) throws BizException {
        if(hasPermission(rolePermissionRequestVO)){
            throw new BizException(RolePermissionResultCode.ROLE_HAS_PERMISSION);
        }
        int result = 0;
        try{
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(rolePermissionRequestVO.getRoleId());
            rolePermission.setPermissionId(rolePermissionRequestVO.getPermissionId());
            result = rolePermissionMapper.insert(rolePermission);
        }catch(Exception e){
            throw new BizException(RolePermissionResultCode.ROLE_PERMISSION_ADD_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean deletePermissionById(Integer id)throws BizException{
        RolePermission rolePermission = rolePermissionMapper.selectByPrimaryKey(id);
        if(rolePermission==null){
            throw new BizException(RolePermissionResultCode.ROLE_PERMISSION_NOT_EXIST);
        }
        int result = 0;
        try{
            result = rolePermissionMapper.deleteByPrimaryKey(id);
        }catch(Exception e){
            throw new BizException(RolePermissionResultCode.ROLE_PERMISSION_DELETE_ERROR);
        }
        return result>0;
    }

    @Override
    public Boolean deletePermissionByRecord(RolePermissionRequestVO rolePermissionRequestVO) throws BizException {
        checkExists(rolePermissionRequestVO);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(rolePermissionRequestVO.getRoleId());
        rolePermission.setPermissionId(rolePermissionRequestVO.getPermissionId());
        int result = 0;
        try{
            result = rolePermissionMapper.deleteRolePermission(rolePermission);
        }catch(Exception e){
            throw new BizException(RolePermissionResultCode.ROLE_PERMISSION_DELETE_ERROR);
        }
        return result>0;
    }

}
