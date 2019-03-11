package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.DoorRecord;
import com.loki.sass.domain.model.DoorRecordExample;

import java.util.Date;
import java.util.List;

import com.loki.sass.domain.po.DoorRecordPO;
import org.apache.ibatis.annotations.Param;

public interface DoorRecordMapper {
    long countByExample(DoorRecordExample example);

    int deleteByExample(DoorRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DoorRecord record);

    int insertSelective(DoorRecord record);

    List<DoorRecord> selectByExample(DoorRecordExample example);

    DoorRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DoorRecord record, @Param("example") DoorRecordExample example);

    int updateByExample(@Param("record") DoorRecord record, @Param("example") DoorRecordExample example);

    int updateByPrimaryKeySelective(DoorRecord record);

    int updateByPrimaryKey(DoorRecord record);

    List<DoorRecordPO> selectByParam(@Param("doorId") Integer doorId,
                                     @Param("doorCode") String doorCode,
                                     @Param("doorName") String doorName,
                                     @Param("regionId") Integer regionId,
                                     @Param("regionName") String regionName,
                                     @Param("userId") Integer userId,
                                     @Param("userPhone") String userPhone,
                                     @Param("userRealName") String userRealName,
                                     @Param("createTimeStart") String createTimeStart,
                                     @Param("createTimeEnd") String createTimeEnd,
                                     @Param("success") Integer success,
                                     @Param("zoneId") Integer zoneId,
                                     @Param("propertyId") Integer propertyId);
}