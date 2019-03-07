package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Door;
import com.loki.sass.domain.model.DoorExample;
import java.util.List;

import com.loki.sass.domain.po.DoorPO;
import org.apache.ibatis.annotations.Param;

public interface DoorMapper {
    long countByExample(DoorExample example);

    int deleteByExample(DoorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Door record);

    int insertSelective(Door record);

    List<Door> selectByExample(DoorExample example);

    Door selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Door record, @Param("example") DoorExample example);

    int updateByExample(@Param("record") Door record, @Param("example") DoorExample example);

    int updateByPrimaryKeySelective(Door record);

    int updateByPrimaryKey(Door record);

    int checkName(@Param("name") String name,@Param("regionId") Integer regionId);

    List<DoorPO> selectByParam(@Param("name") String name,@Param("code") String code,@Param("remoteIp") String remoteIp,@Param("regionName") String regionName,@Param("regionId") Integer regionId, @Param("createByName") String createByName, @Param("updateByName") String updateByName, @Param("state") Integer state);
}