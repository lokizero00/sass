package com.loki.sass.domain.mapper;

import com.loki.sass.domain.model.WechatAuth;
import com.loki.sass.domain.model.WechatAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WechatAuthMapper {
    long countByExample(WechatAuthExample example);

    int deleteByExample(WechatAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WechatAuth record);

    int insertSelective(WechatAuth record);

    List<WechatAuth> selectByExample(WechatAuthExample example);

    WechatAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WechatAuth record, @Param("example") WechatAuthExample example);

    int updateByExample(@Param("record") WechatAuth record, @Param("example") WechatAuthExample example);

    int updateByPrimaryKeySelective(WechatAuth record);

    int updateByPrimaryKey(WechatAuth record);

    WechatAuth selectWechatAuthByOpenId(@Param("openId") String openId);

    WechatAuth selectBindWechatAuthByOpenId(@Param("openId") String openId);

    WechatAuth selectWechatAuthByUserId(@Param("userId") Integer userId);
}