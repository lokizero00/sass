package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.VisitorApply;
import com.loki.sass.domain.model.VisitorApplyExample;

import java.util.Date;
import java.util.List;

import com.loki.sass.domain.po.VisitorApplyPO;
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
    List<VisitorApplyPO> selectByParam(@Param("visitorName") String visitorName,
                                       @Param("visitorPhone") String visitorPhone,
                                       @Param("visitorUserId") Integer visitorUserId,
                                       @Param("regionId") Integer regionId,
                                       @Param("regionName") String regionName,
                                       @Param("intervieweeName") String intervieweeName,
                                       @Param("intervieweePhone") String intervieweePhone,
                                       @Param("intervieweeId") Integer intervieweeId,
                                       @Param("visitingTimeStart") Date visitingTimeStart,
                                       @Param("visitingTimeEnd") Date visitingTimeEnd,
                                       @Param("updateByName") String updateByName,
                                       @Param("state") Integer state);

}