package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.PermissionVO;
import com.loki.sass.domain.mapper.PermissionMapper;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.domain.model.PermissionExample;
import com.loki.sass.service.manager.api.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> selectByRoleId(Integer roleId) {
        List<Permission> permissionList=permissionMapper.selectByRoleId(roleId);
        List<PermissionDTO> permissionDTOList= ConvertUtils.sourceToTarget(permissionList,PermissionDTO.class);
        return permissionDTOList;
    }

    @Override
    public Integer insert(PermissionVO permissionVO) {
        Permission permission = ConvertUtils.sourceToTarget(permissionVO, Permission.class);
        permission.setAvailable(1);
        permission.setCreateBy(0);
        permission.setIsDeleted(0);
        Integer result = permissionMapper.insert(permission);
        logger.info("[permissionService插入记录],permissionVO={},result={}",permissionVO,result);
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        logger.info("[permissionService删除记录],id={}",id);
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(PermissionVO permissionVO) {
        if(permissionVO==null || permissionVO.getId()==null){
            throw new NullPointerException("权限id为空");
        }
        Permission permission = permissionMapper.selectByPrimaryKey(permissionVO.getId());
        permission.setName(permissionVO.getName());
        permission.setResourceType(permissionVO.getResourceType());
        permission.setUrl(permissionVO.getUrl());
        permission.setPermission(permissionVO.getPermission());
        permission.setParentId(permissionVO.getParentId());
        permission.setIsRegional(permissionVO.getIsRegional());
        int result = permissionMapper.updateByPrimaryKey(permission);
        logger.info("[permissionService更新记录],permissionVO={},result={}",permissionVO,result);

        return result;
    }

    @Override
    public PermissionDTO selectById(Integer id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        logger.info("[permissionService查询记录],id={}",id);
        PermissionDTO permissionDTO = ConvertUtils.sourceToTarget(permission, PermissionDTO.class);
        return permissionDTO;
    }

    @Override
    public List<PermissionDTO> findAll() {
        List<Permission> permissionList = permissionMapper.selectByExample(new PermissionExample());
        List<PermissionDTO> permissionDTOList = ConvertUtils.sourceToTarget(permissionList, PermissionDTO.class);
        return permissionDTOList;
    }

    @Override
    public List<PermissionDTO> findByPage(Integer current, Integer count) {
        if(current==null){
            current = 1;
        }
        if(count==null){
            count = 10;
        }
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.setOffset((current-1)*count);
        permissionExample.setLimit(count);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<PermissionDTO> rtnList = ConvertUtils.sourceToTarget(permissionList, PermissionDTO.class);
        return rtnList;
    }
}
