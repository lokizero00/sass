package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.domain.mapper.PermissionMapper;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.service.manager.api.PermissionService;
import com.loki.sass.service.manager.convertor.PermissionConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionConvertor permissionConvertor;

    @Override
    public List<PermissionDTO> selectByRoleId(Integer roleId) {
        List<Permission> permissionList=permissionMapper.selectByRoleId(roleId);
        List<PermissionDTO> permissionDTOList= permissionConvertor.from(permissionList);
        return permissionDTOList;
    }
}
