package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.domain.model.Role;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface RoleService {
    List<RoleDTO> selectByUserId(Integer adminId);
}
