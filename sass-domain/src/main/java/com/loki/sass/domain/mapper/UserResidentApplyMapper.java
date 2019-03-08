package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.UserResidentApply;
import com.loki.sass.domain.model.UserResidentApplyExample;
import java.util.List;

import com.loki.sass.domain.po.UserResidentApplyPO;
import org.apache.ibatis.annotations.Param;

public interface UserResidentApplyMapper {
    long countByExample(UserResidentApplyExample example);

    int deleteByExample(UserResidentApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserResidentApply record);

    int insertSelective(UserResidentApply record);

    List<UserResidentApply> selectByExample(UserResidentApplyExample example);

    UserResidentApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserResidentApply record, @Param("example") UserResidentApplyExample example);

    int updateByExample(@Param("record") UserResidentApply record, @Param("example") UserResidentApplyExample example);

    int updateByPrimaryKeySelective(UserResidentApply record);

    int updateByPrimaryKey(UserResidentApply record);

    List<UserResidentApplyPO> selectByParam(@Param("userRealName") String userRealName, @Param("userPhone") String userPhone, @Param("regionName") String regionName, @Param("regionId") Integer regionId, @Param("updateByName") String updateByName, @Param("state") Integer state);
}