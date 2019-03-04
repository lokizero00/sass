package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.TipInfo;
import com.loki.sass.domain.model.TipInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipInfoMapper {
    long countByExample(TipInfoExample example);

    int deleteByExample(TipInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TipInfo record);

    int insertSelective(TipInfo record);

    List<TipInfo> selectByExample(TipInfoExample example);

    TipInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TipInfo record, @Param("example") TipInfoExample example);

    int updateByExample(@Param("record") TipInfo record, @Param("example") TipInfoExample example);

    int updateByPrimaryKeySelective(TipInfo record);

    int updateByPrimaryKey(TipInfo record);

    List<TipInfo> selectBySearch(@Param("search")String search);
}