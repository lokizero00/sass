package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.SysOperatLog;
import com.loki.sass.domain.model.SysOperatLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperatLogMapper {
    long countByExample(SysOperatLogExample example);

    int deleteByExample(SysOperatLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOperatLog record);

    int insertSelective(SysOperatLog record);

    List<SysOperatLog> selectByExample(SysOperatLogExample example);

    SysOperatLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOperatLog record, @Param("example") SysOperatLogExample example);

    int updateByExample(@Param("record") SysOperatLog record, @Param("example") SysOperatLogExample example);

    int updateByPrimaryKeySelective(SysOperatLog record);

    int updateByPrimaryKey(SysOperatLog record);
}