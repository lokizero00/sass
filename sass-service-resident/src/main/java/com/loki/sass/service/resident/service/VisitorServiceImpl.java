package com.loki.sass.service.resident.service;

import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.enums.UserState;
import com.loki.sass.common.enums.UserType;
import com.loki.sass.common.enums.VisitorApplyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.VisitApplyRequestVO;
import com.loki.sass.domain.mapper.*;
import com.loki.sass.domain.model.*;
import com.loki.sass.service.resident.api.VisitorService;
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
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    VisitorApplyMapper visitorApplyMapper;
    @Autowired
    WechatAuthMapper wechatAuthMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    RegionMapper regionMapper;

    @Override
    public boolean visitApply(VisitApplyRequestVO visitApplyRequestVO) throws BizException {
        Region region=regionMapper.selectByPrimaryKey(visitApplyRequestVO.getRegionId());
        if(null==region){
            throw new BizException(VisitorResultCode.VISIT_REGION_NOT_EXISTS);
        }

        User intervieweeUser=userMapper.selectByMobile(visitApplyRequestVO.getIntervieweeMobile());
        if(null==intervieweeUser){
            throw new BizException(VisitorResultCode.VISIT_INTERVIEWEE_NOT_EXISTS);
        }

        try {

            WechatAuth wechatAuth = wechatAuthMapper.selectWechatAuthByOpenId(visitApplyRequestVO.getOpenId());

            if (null == wechatAuth) {
                User user = new User();
                user.setMobile(visitApplyRequestVO.getMobile());
                user.setAvatarUrl(visitApplyRequestVO.getAvatarUrl());
                user.setNickName(visitApplyRequestVO.getNickName());
                user.setType(UserType.RESIDENT.getValue());
                user.setRegistryTime(new Date());
                user.setState(UserState.USING.getValue());
                userMapper.insert(user);

                UserAuth userAuth = new UserAuth();
                userAuth.setSex(visitApplyRequestVO.getSex());
                userAuth.setRealName(visitApplyRequestVO.getRealName());
                userAuth.setProvince(visitApplyRequestVO.getProvince());
                userAuth.setNation(visitApplyRequestVO.getNation());
                userAuth.setCity(visitApplyRequestVO.getCity());
                userAuth.setCardType(null != userAuth.getCardType() ? userAuth.getCardType() : 0);
                userAuth.setUserId(user.getId());
                userAuthMapper.insert(userAuth);

                wechatAuth = new WechatAuth();
                wechatAuth.setOpenId(visitApplyRequestVO.getOpenId());
                wechatAuth.setUserId(user.getId());
                wechatAuthMapper.insert(wechatAuth);
            }

            VisitorApply visitorApply = new VisitorApply();
            visitorApply.setVisitorName(visitApplyRequestVO.getRealName());
            visitorApply.setVisitorPhone(visitApplyRequestVO.getMobile());
            visitorApply.setVisitorUserId(wechatAuth.getUserId());
            visitorApply.setRegionId(region.getId());
            visitorApply.setIntervieweeId(intervieweeUser.getId());
            visitorApply.setPurpose(visitApplyRequestVO.getPurpose());
            visitorApply.setCreateTime(new Date());
            visitorApply.setVisitingTime(visitApplyRequestVO.getVisitingTime());
            visitorApply.setState(VisitorApplyState.VERIFY.getValue());
            visitorApplyMapper.insert(visitorApply);
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException(VisitorResultCode.VISIT_APPLY_ERROR);
        }

        return true;
    }
}
