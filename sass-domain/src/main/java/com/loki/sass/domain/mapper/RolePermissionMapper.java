package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.RolePermission;
import com.loki.sass.domain.model.RolePermissionExample;
import com.loki.sass.domain.model.RolePermissionRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    long countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    int count(RolePermission record);

    int deleteRoleOwnPermissions(@Param("roleId") Integer roleId);

    int addRoleOwnPermissions(@Param("rolePermissionRequest")RolePermissionRequest rolePermissionRequest);
}