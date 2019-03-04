package com.loki.sass.service.resident.service;

import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.enums.UserResidentApplyState;
import com.loki.sass.common.enums.UserState;
import com.loki.sass.common.enums.UserType;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.ResidentJoinRequestVO;
import com.loki.sass.domain.mapper.*;
import com.loki.sass.domain.model.*;
import com.loki.sass.service.resident.api.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * created by lokizero00 on 2019-02-27
 */
@Slf4j
@Service
@Transactional
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    WechatAuthMapper wechatAuthMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    UserResidentApplyMapper userResidentApplyMapper;

    @Override
    public boolean joinApply(ResidentJoinRequestVO residentJoinRequestVO) {
        WechatAuth oldWechatAuth=wechatAuthMapper.selectWechatAuthByOpenId(residentJoinRequestVO.getOpenId());
        if(null!=oldWechatAuth){
            throw new BizException(ResidentResultCode.WECHAT_EXISTS);
        }

        Region region=regionMapper.selectByPrimaryKey(residentJoinRequestVO.getRegionId());
        if(null==region){
            throw new BizException(ResidentResultCode.REGISTER_REGION_NOT_EXISTS);
        }

        try{
            User user=new User();
            user.setMobile(residentJoinRequestVO.getMobile());
            user.setAvatarUrl(residentJoinRequestVO.getAvatarUrl());
            user.setNickName(residentJoinRequestVO.getNickName());
            user.setRegistryTime(new Date());
            user.setType(UserType.RESIDENT.getValue());
            user.setState(UserState.USING.getValue());
            userMapper.insert(user);

            UserAuth userAuth=new UserAuth();
            userAuth.setRealName(residentJoinRequestVO.getRealName());
            userAuth.setSex(residentJoinRequestVO.getSex());
            userAuth.setNation(residentJoinRequestVO.getNation());
            userAuth.setProvince(residentJoinRequestVO.getProvince());
            userAuth.setCity(residentJoinRequestVO.getCity());
            userAuth.setUserId(user.getId());
            userAuth.setCardType(null!=userAuth.getCardType() ? userAuth.getCardType():0);
            userAuthMapper.insert(userAuth);

            WechatAuth wechatAuth=new WechatAuth();
            wechatAuth.setOpenId(residentJoinRequestVO.getOpenId());
            wechatAuth.setUserId(user.getId());
            wechatAuthMapper.insert(wechatAuth);

            UserResidentApply userResidentApply=new UserResidentApply();
            userResidentApply.setUserId(user.getId());
            userResidentApply.setRegionId(region.getId());
            userResidentApply.setCreateTime(new Date());
            userResidentApply.setState(UserResidentApplyState.VERIFY.getValue());
            userResidentApplyMapper.insert(userResidentApply);

        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(ResidentResultCode.REGISTER_JOIN_ERROR);
        }

        return true;
    }
}
