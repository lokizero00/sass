package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.AdminExample;
import com.loki.sass.domain.po.AdminPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByMobile(@Param("mobile") String mobile);

    List<AdminPO> selectByParam(@Param("username") String username, @Param("createByName") String createByName, @Param("updateByName") String updateByName, @Param("state") Integer state);

    int checkName(@Param("username") String username);

    int count(@Param("id")Integer id);
}