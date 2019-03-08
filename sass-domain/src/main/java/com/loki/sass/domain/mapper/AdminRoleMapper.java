package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.AdminRole;
import com.loki.sass.domain.model.AdminRoleExample;
import com.loki.sass.domain.model.AdminRoleRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {
    long countByExample(AdminRoleExample example);

    int deleteByExample(AdminRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    List<AdminRole> selectByExample(AdminRoleExample example);

    AdminRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);

    int updateByExample(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);

    int deleteAdminOwnRoles(@Param("adminId") Integer adminId);

    int addAdminOwnRoles(@Param("adminRoleRequest")AdminRoleRequest adminRoleRequest);
}