package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.UserDoor;
import com.loki.sass.domain.model.UserDoorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDoorMapper {
    long countByExample(UserDoorExample example);

    int deleteByExample(UserDoorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDoor record);

    int insertSelective(UserDoor record);

    List<UserDoor> selectByExample(UserDoorExample example);

    UserDoor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDoor record, @Param("example") UserDoorExample example);

    int updateByExample(@Param("record") UserDoor record, @Param("example") UserDoorExample example);

    int updateByPrimaryKeySelective(UserDoor record);

    int updateByPrimaryKey(UserDoor record);

    List<UserDoor> selectByUserId(@Param("userId") Integer userId);
}