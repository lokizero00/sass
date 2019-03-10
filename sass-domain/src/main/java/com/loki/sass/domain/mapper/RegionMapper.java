package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.Region;
import com.loki.sass.domain.model.RegionExample;
import java.util.List;

import com.loki.sass.domain.po.RegionPO;
import org.apache.ibatis.annotations.Param;

public interface RegionMapper {
    long countByExample(RegionExample example);

    int deleteByExample(RegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    List<Region> selectByExample(RegionExample example);

    Region selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<RegionPO> selectByParentId(@Param("parentId") Integer parentId);

    List<RegionPO> selectRootAll();

    List<RegionPO> selectRootByZoneId(@Param("zoneId") Integer zoneId);

    List<RegionPO> selectRootByPropertyId(@Param("propertyId") Integer propertyId);
}