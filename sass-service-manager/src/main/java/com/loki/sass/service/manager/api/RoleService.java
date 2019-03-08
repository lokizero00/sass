package com.loki.sass.service.manager.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.RolePermissionRequestVO;
import com.loki.sass.common.vo.RoleQueryVO;
import com.loki.sass.common.vo.RoleRequestVO;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface RoleService {
    List<RoleDTO> selectByUserId(Integer adminId)throws BizException;

    Boolean insert(RoleRequestVO roleRequestVO)throws BizException;

    Boolean update(RoleRequestVO roleRequestVO)throws BizException;

    Boolean deleteById(Integer id,Integer operatorId)throws BizException;

    List<RoleDTO> findAll()throws BizException;

    PageInfo<RoleDTO> getAdminListSearch(RoleQueryVO roleQueryVO)throws BizException;

    RoleDTO findById(Integer id)throws BizException;

    List<PermissionDTO> findOwnPermissions(Integer roleId)throws BizException;

    Boolean updateOwnPermissions(RolePermissionRequestVO rolePermissionRequestVO)throws BizException;
}
