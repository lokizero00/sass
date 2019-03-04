package com.loki.sass.domain.mapper;

import com.loki.sass.domain.po.ZonePO;
import com.loki.sass.domain.model.Zone;
import com.loki.sass.domain.model.ZoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZoneMapper {
    long countByExample(ZoneExample example);

    int deleteByExample(ZoneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Zone record);

    int insertSelective(Zone record);

    List<Zone> selectByExample(ZoneExample example);

    Zone selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Zone record, @Param("example") ZoneExample example);

    int updateByExample(@Param("record") Zone record, @Param("example") ZoneExample example);

    int updateByPrimaryKeySelective(Zone record);

    int updateByPrimaryKey(Zone record);

    List<ZonePO> selectByParam(@Param("name") String name, @Param("createByName") String createByName, @Param("updateByName") String updateByName, @Param("state") Integer state);
}