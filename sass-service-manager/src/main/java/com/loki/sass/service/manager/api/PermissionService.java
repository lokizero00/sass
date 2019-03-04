package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.domain.model.Permission;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface PermissionService {
    List<PermissionDTO> selectByRoleId(Integer roleId);
}
