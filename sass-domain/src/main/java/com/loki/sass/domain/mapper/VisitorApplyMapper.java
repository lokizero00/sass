package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.VisitorApply;
import com.loki.sass.domain.model.VisitorApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VisitorApplyMapper {
    long countByExample(VisitorApplyExample example);

    int deleteByExample(VisitorApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VisitorApply record);

    int insertSelective(VisitorApply record);

    List<VisitorApply> selectByExample(VisitorApplyExample example);

    VisitorApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitorApply record, @Param("example") VisitorApplyExample example);

    int updateByExample(@Param("record") VisitorApply record, @Param("example") VisitorApplyExample example);

    int updateByPrimaryKeySelective(VisitorApply record);

    int updateByPrimaryKey(VisitorApply record);
}