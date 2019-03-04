package com.loki.sass.service.manager.convertor;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.domain.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-26
 */
@Mapper(componentModel = "spring")
public interface RoleConvertor {
    public List<RoleDTO> from(List<Role> roleList);
}
