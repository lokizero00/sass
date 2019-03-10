package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Property;
import com.loki.sass.domain.model.PropertyExample;
import java.util.List;

import com.loki.sass.domain.po.PropertyPO;
import org.apache.ibatis.annotations.Param;

public interface PropertyMapper {
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByExample(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);

    int checkName(@Param("name") String name,@Param("zoneId") Integer zoneId);

    List<PropertyPO> selectByParam(@Param("name") String name, @Param("createByName") String createByName, @Param("updateByName") String updateByName,@Param("zoneName") String zoneName,@Param("zoneId") Integer zoneId,@Param("propertyId") Integer propertyId, @Param("state") Integer state);
}