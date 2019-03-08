package com.loki.sass.service.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.enums.VisitorApplyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.VisitorApplyQueryVO;
import com.loki.sass.domain.mapper.VisitorApplyMapper;
import com.loki.sass.domain.model.VisitorApply;
import com.loki.sass.domain.po.VisitorApplyPO;
import com.loki.sass.service.business.api.VisitorApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * created by lokizero00 on 2019-03-07
 */
@Slf4j
@Service
@Transactional
public class VisitorApplyServiceImpl implements VisitorApplyService {
    @Autowired
    VisitorApplyMapper visitorApplyMapper;

    @Override
    public boolean applyPass(Integer applyId,String reason, Integer adminId) throws BizException {
        VisitorApply visitorApply=visitorApplyMapper.selectByPrimaryKey(applyId);
        if(null==visitorApply){
            throw new BizException(VisitorResultCode.VISIT_APPLY_NOT_EXISTS);
        }

        try{
            visitorApply.setUpdateBy(adminId);
            visitorApply.setUpdateTime(new Date());
            visitorApply.setReason(reason);
            visitorApply.setState(VisitorApplyState.PASS.getValue());
            visitorApplyMapper.updateByPrimaryKeySelective(visitorApply);

            //TODO 处理访客门禁权限
        }catch(Exception e){
            throw new BizException(VisitorResultCode.VISIT_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public boolean applyRefuse(Integer applyId,String reason, Integer adminId) throws BizException {
        VisitorApply visitorApply=visitorApplyMapper.selectByPrimaryKey(applyId);
        if(null==visitorApply){
            throw new BizException(VisitorResultCode.VISIT_APPLY_NOT_EXISTS);
        }

        try{
            visitorApply.setUpdateBy(adminId);
            visitorApply.setUpdateTime(new Date());
            visitorApply.setReason(reason);
            visitorApply.setState(VisitorApplyState.REFUSE.getValue());
            visitorApplyMapper.updateByPrimaryKeySelective(visitorApply);
        }catch(Exception e){
            throw new BizException(VisitorResultCode.VISIT_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public PageInfo<VisitorApplyDTO> getApplyListSearch(VisitorApplyQueryVO visitorApplyQueryVO) throws BizException {
        if (!StringUtils.isEmpty(visitorApplyQueryVO.getPage()) && !StringUtils.isEmpty(visitorApplyQueryVO.getRows())) {
            PageHelper.startPage(visitorApplyQueryVO.getPage(), visitorApplyQueryVO.getRows());
        }
        List<VisitorApplyPO> list=visitorApplyMapper.selectByParam(visitorApplyQueryVO.getVisitorName(),visitorApplyQueryVO.getVisitorPhone(),visitorApplyQueryVO.getVisitorUserId(),visitorApplyQueryVO.getRegionId(),visitorApplyQueryVO.getRegionName(),visitorApplyQueryVO.getIntervieweeName(),visitorApplyQueryVO.getIntervieweePhone(),visitorApplyQueryVO.getIntervieweeId(),visitorApplyQueryVO.getVisitingTimeStart(),visitorApplyQueryVO.getVisitingTimeEnd(),visitorApplyQueryVO.getUpdateByName(),visitorApplyQueryVO.getState());
        List<VisitorApplyDTO> dtoList= ConvertUtils.sourceToTarget(list,VisitorApplyDTO.class);
        PageInfo<VisitorApplyDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
