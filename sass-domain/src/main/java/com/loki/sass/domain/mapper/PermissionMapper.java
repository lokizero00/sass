package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Permission;
import com.loki.sass.domain.model.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByRoleId(@Param("roleId") Integer roleId);

    List<Permission> selectButtonByRoleId(@Param("roleId") Integer roleId);

    int checkName(@Param("name") String name);

    int count(@Param("id")Integer id);

    List<Permission> selectByRoleIds(@Param("roleIdList") List<Integer> roleIdList);

    int countForList(@Param("ids")List<Integer> ids);

    List<Permission> selectRootList(@Param("isRegional") Integer isRegional);

    List<Permission> selectListByParentId(@Param("permissionId") Integer PermissionId);
}