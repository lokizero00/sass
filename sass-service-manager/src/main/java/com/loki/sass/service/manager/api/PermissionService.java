package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.vo.PermissionVO;
import com.loki.sass.domain.model.Permission;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface PermissionService {
    List<PermissionDTO> selectByRoleId(Integer roleId);

    Integer insert(PermissionVO permissionVO);

    Integer deleteById(Integer id);

    Integer update(PermissionVO permissionVO);

    PermissionDTO selectById(Integer id);

    List<PermissionDTO> findAll();

    List<PermissionDTO> findByPage(Integer current,Integer count);
}
