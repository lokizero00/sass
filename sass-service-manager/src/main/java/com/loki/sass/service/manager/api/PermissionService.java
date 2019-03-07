package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.PermissionRequestVO;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface PermissionService {
    List<PermissionDTO> selectByRoleId(Integer roleId)throws BizException;

    Boolean insert(PermissionRequestVO permissionRequestVO)throws BizException;

    Boolean delete(Integer which,Integer operatorId)throws BizException;

    Boolean update(PermissionRequestVO permissionRequestVO)throws BizException;

    PermissionDTO findOne(Integer id)throws BizException;

    List<PermissionDTO> findAll()throws BizException;
}
