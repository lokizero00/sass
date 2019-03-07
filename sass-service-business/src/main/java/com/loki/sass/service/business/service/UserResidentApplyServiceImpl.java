package com.loki.sass.service.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.dto.UserResidentApplyDTO;
import com.loki.sass.common.enums.UserResidentApplyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;
import com.loki.sass.domain.mapper.UserResidentApplyMapper;
import com.loki.sass.domain.model.UserResidentApply;
import com.loki.sass.domain.po.UserResidentApplyPO;
import com.loki.sass.service.business.api.UserResidentApplyService;
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
public class UserResidentApplyServiceImpl implements UserResidentApplyService {
    @Autowired
    UserResidentApplyMapper userResidentApplyMapper;

    @Override
    public boolean applyPass(Integer applyId, Integer adminId) throws BizException {
        UserResidentApply userResidentApply=userResidentApplyMapper.selectByPrimaryKey(applyId);
        if(null==userResidentApply){
            throw new BizException(ResidentResultCode.REGISTER_APPLY_NOT_EXISTS);
        }

        try{
            userResidentApply.setUpdateBy(adminId);
            userResidentApply.setUpdateTime(new Date());
            userResidentApply.setState(UserResidentApplyState.USING.getValue());
            userResidentApplyMapper.updateByPrimaryKeySelective(userResidentApply);
        }catch(Exception e){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public boolean applyRefuse(Integer applyId, Integer adminId) throws BizException {
        UserResidentApply userResidentApply=userResidentApplyMapper.selectByPrimaryKey(applyId);
        if(null==userResidentApply){
            throw new BizException(ResidentResultCode.REGISTER_APPLY_NOT_EXISTS);
        }

        try{
            userResidentApply.setUpdateBy(adminId);
            userResidentApply.setUpdateTime(new Date());
            userResidentApply.setState(UserResidentApplyState.FORBIDDEN.getValue());
            userResidentApplyMapper.updateByPrimaryKeySelective(userResidentApply);
        }catch(Exception e){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public PageInfo<UserResidentApplyDTO> getApplyListSearch(UserResidentApplyQueryVO userResidentApplyQueryVO) throws BizException {
        if (!StringUtils.isEmpty(userResidentApplyQueryVO.getPage()) && !StringUtils.isEmpty(userResidentApplyQueryVO.getRows())) {
            PageHelper.startPage(userResidentApplyQueryVO.getPage(), userResidentApplyQueryVO.getRows());
        }
        List<UserResidentApplyPO> list=userResidentApplyMapper.selectByParam(userResidentApplyQueryVO.getUserRealName(),userResidentApplyQueryVO.getUserPhone(),userResidentApplyQueryVO.getRegionName(),userResidentApplyQueryVO.getRegionId(),userResidentApplyQueryVO.getUpdateByName(),userResidentApplyQueryVO.getState());
        List<UserResidentApplyDTO> dtoList= ConvertUtils.sourceToTarget(list,UserResidentApplyDTO.class);
        PageInfo<UserResidentApplyDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
