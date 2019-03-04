package com.loki.sass.service.manager.convertor;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.domain.model.Admin;
import org.mapstruct.Mapper;

/**
 * created by lokizero00 on 2019-02-26
 */
@Mapper(componentModel = "spring")
public interface AdminConvertor {
    public AdminDTO from(Admin admin);
}
