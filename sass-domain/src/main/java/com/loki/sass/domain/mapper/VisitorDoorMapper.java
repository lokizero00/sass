package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.VisitorDoor;
import com.loki.sass.domain.model.VisitorDoorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VisitorDoorMapper {
    long countByExample(VisitorDoorExample example);

    int deleteByExample(VisitorDoorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VisitorDoor record);

    int insertSelective(VisitorDoor record);

    List<VisitorDoor> selectByExample(VisitorDoorExample example);

    VisitorDoor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitorDoor record, @Param("example") VisitorDoorExample example);

    int updateByExample(@Param("record") VisitorDoor record, @Param("example") VisitorDoorExample example);

    int updateByPrimaryKeySelective(VisitorDoor record);

    int updateByPrimaryKey(VisitorDoor record);
}