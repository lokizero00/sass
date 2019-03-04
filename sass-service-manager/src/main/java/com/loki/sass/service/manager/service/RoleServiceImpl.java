package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.domain.mapper.RoleMapper;
import com.loki.sass.domain.model.Role;
import com.loki.sass.service.manager.api.RoleService;
import com.loki.sass.service.manager.convertor.RoleConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleConvertor roleConvertor;

    @Override
    public List<RoleDTO> selectByUserId(Integer adminId) {
        List<Role> roleList=roleMapper.selectByAdminId(adminId);
        List<RoleDTO> roleDTOList= roleConvertor.from(roleList);
        return roleDTOList;
    }
}
