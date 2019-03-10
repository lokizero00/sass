package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Role;
import com.loki.sass.domain.model.RoleExample;
import com.loki.sass.domain.po.RolePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByAdminId(@Param("adminId") Integer adminId);

    List<String> selectRoleByAdminId(@Param("adminId") Integer adminId);

    List<RolePO> selectByParam(@Param("role") String role,@Param("createByName") String createByName, @Param("updateByName") String updateByName);

    int checkRole(@Param("role") String role);

    int count(@Param("id")Integer id);

    int countForList(@Param("ids")List<Integer> ids);
}