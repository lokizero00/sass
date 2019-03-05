package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.vo.RoleVO;
import com.loki.sass.domain.model.Role;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface RoleService {
    List<RoleDTO> selectByUserId(Integer adminId);

    Integer insert(RoleVO roleVO);

    Integer update(RoleVO roleVO);

    Integer deleteById(Integer id);

    List<RoleDTO> findAll();

    List<RoleDTO> findByPage(Integer current,Integer count);

    RoleDTO findById(Integer id);
}
