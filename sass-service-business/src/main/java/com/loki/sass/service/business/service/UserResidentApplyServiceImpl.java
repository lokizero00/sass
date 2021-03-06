package com.loki.sass.service.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserResidentApplyDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.enums.UserResidentApplyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.UserRegionMapper;
import com.loki.sass.domain.mapper.UserResidentApplyMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.UserRegion;
import com.loki.sass.domain.model.UserResidentApply;
import com.loki.sass.domain.po.UserResidentApplyPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.business.api.UserResidentApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    @Autowired
    UserRegionMapper userRegionMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    FeignRoleService feignRoleService;

    @Override
    public boolean applyPass(Integer applyId,String reason, Integer adminId) throws BizException {
        UserResidentApply userResidentApply=userResidentApplyMapper.selectByPrimaryKey(applyId);
        if(null==userResidentApply){
            throw new BizException(ResidentResultCode.REGISTER_APPLY_NOT_EXISTS);
        }

        try{
            userResidentApply.setUpdateBy(adminId);
            userResidentApply.setUpdateTime(new Date());
            userResidentApply.setReason(reason);
            userResidentApply.setState(UserResidentApplyState.USING.getValue());
            userResidentApplyMapper.updateByPrimaryKeySelective(userResidentApply);

            UserRegion userRegion=new UserRegion();
            userRegion.setApplyId(userResidentApply.getId());
            userRegion.setRegionId(userResidentApply.getRegionId());
            userRegion.setUserId(userResidentApply.getUserId());
            userRegionMapper.insertSelective(userRegion);
        }catch(Exception e){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public boolean applyRefuse(Integer applyId,String reason, Integer adminId) throws BizException {
        UserResidentApply userResidentApply=userResidentApplyMapper.selectByPrimaryKey(applyId);
        if(null==userResidentApply){
            throw new BizException(ResidentResultCode.REGISTER_APPLY_NOT_EXISTS);
        }

        try{
            userResidentApply.setUpdateBy(adminId);
            userResidentApply.setUpdateTime(new Date());
            userResidentApply.setReason(reason);
            userResidentApply.setState(UserResidentApplyState.FORBIDDEN.getValue());
            userResidentApplyMapper.updateByPrimaryKeySelective(userResidentApply);
        }catch(Exception e){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_ERROR);
        }
        return true;
    }

    @Override
    public PageInfo<UserResidentApplyDTO> getApplyListSearch(UserResidentApplyQueryVO userResidentApplyQueryVO) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(userResidentApplyQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        if (!StringUtils.isEmpty(userResidentApplyQueryVO.getPage()) && !StringUtils.isEmpty(userResidentApplyQueryVO.getRows())) {
            PageHelper.startPage(userResidentApplyQueryVO.getPage(), userResidentApplyQueryVO.getRows());
        }
        List<UserResidentApplyPO> list=new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                list=userResidentApplyMapper.selectByParam(userResidentApplyQueryVO.getUserRealName(),userResidentApplyQueryVO.getUserPhone(),userResidentApplyQueryVO.getRegionName(),userResidentApplyQueryVO.getRegionId(),userResidentApplyQueryVO.getUpdateByName(),userResidentApplyQueryVO.getState(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                list=userResidentApplyMapper.selectByParam(userResidentApplyQueryVO.getUserRealName(),userResidentApplyQueryVO.getUserPhone(),userResidentApplyQueryVO.getRegionName(),userResidentApplyQueryVO.getRegionId(),userResidentApplyQueryVO.getUpdateByName(),userResidentApplyQueryVO.getState(),admin.getZoneId(),0);
                break;
            case ADMIN:
                list=userResidentApplyMapper.selectByParam(userResidentApplyQueryVO.getUserRealName(),userResidentApplyQueryVO.getUserPhone(),userResidentApplyQueryVO.getRegionName(),userResidentApplyQueryVO.getRegionId(),userResidentApplyQueryVO.getUpdateByName(),userResidentApplyQueryVO.getState(),0,0);
                break;
            default:
                break;
        }
        PageInfo<UserResidentApplyPO> userResidentApplyPOPageInfo = new PageInfo<>(list);
        return ConvertUtils.sourceToTarget(userResidentApplyPOPageInfo,UserResidentApplyDTO.class);

    }
}
