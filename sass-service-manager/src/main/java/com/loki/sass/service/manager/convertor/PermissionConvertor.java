package com.loki.sass.service.manager.convertor;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.domain.model.Permission;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-26
 */
@Mapper(componentModel = "spring")
public interface PermissionConvertor {
    public List<PermissionDTO> from(List<Permission> permissionList);
}
