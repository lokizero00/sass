package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.UserRegion;
import com.loki.sass.domain.model.UserRegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRegionMapper {
    long countByExample(UserRegionExample example);

    int deleteByExample(UserRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRegion record);

    int insertSelective(UserRegion record);

    List<UserRegion> selectByExample(UserRegionExample example);

    UserRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRegion record, @Param("example") UserRegionExample example);

    int updateByExample(@Param("record") UserRegion record, @Param("example") UserRegionExample example);

    int updateByPrimaryKeySelective(UserRegion record);

    int updateByPrimaryKey(UserRegion record);
}