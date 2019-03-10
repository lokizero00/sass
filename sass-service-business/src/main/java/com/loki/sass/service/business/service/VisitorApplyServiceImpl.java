package com.loki.sass.service.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.enums.VisitorApplyState;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.VisitorApplyQueryVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.UserDoorMapper;
import com.loki.sass.domain.mapper.VisitorApplyMapper;
import com.loki.sass.domain.mapper.VisitorDoorMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.UserDoor;
import com.loki.sass.domain.model.VisitorApply;
import com.loki.sass.domain.model.VisitorDoor;
import com.loki.sass.domain.po.VisitorApplyPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.business.api.VisitorApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    UserDoorMapper userDoorMapper;
    @Autowired
    VisitorDoorMapper visitorDoorMapper;

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    FeignRoleService feignRoleService;

    @Override
    public boolean applyPass(Integer applyId,Integer waitHour,String reason, Integer adminId) throws BizException {
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

            VisitorDoor visitorDoor=new VisitorDoor();
            Calendar visitorCalendar = Calendar.getInstance();
            visitorCalendar.setTime(visitorApply.getVisitingTime());
            visitorCalendar.add(Calendar.HOUR,waitHour);

            visitorDoor.setStartTime(visitorApply.getVisitingTime());
            visitorDoor.setEndTime(visitorCalendar.getTime());
            visitorDoor.setUserId(visitorApply.getVisitorUserId());

            List<UserDoor> intervieweeDoorList=userDoorMapper.selectByUserId(visitorApply.getIntervieweeId());
            for(UserDoor userDoor:intervieweeDoorList){
                visitorDoor.setDoorId(userDoor.getDoorId());
                visitorDoorMapper.insertSelective(visitorDoor);
            }
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
        Admin admin=adminMapper.selectByPrimaryKey(visitorApplyQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        if (!StringUtils.isEmpty(visitorApplyQueryVO.getPage()) && !StringUtils.isEmpty(visitorApplyQueryVO.getRows())) {
            PageHelper.startPage(visitorApplyQueryVO.getPage(), visitorApplyQueryVO.getRows());
        }
        List<VisitorApplyPO> list=new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                list=visitorApplyMapper.selectByParam(visitorApplyQueryVO.getVisitorName(),visitorApplyQueryVO.getVisitorPhone(),visitorApplyQueryVO.getVisitorUserId(),visitorApplyQueryVO.getRegionId(),visitorApplyQueryVO.getRegionName(),visitorApplyQueryVO.getIntervieweeName(),visitorApplyQueryVO.getIntervieweePhone(),visitorApplyQueryVO.getIntervieweeId(),visitorApplyQueryVO.getVisitingTimeStart(),visitorApplyQueryVO.getVisitingTimeEnd(),visitorApplyQueryVO.getUpdateByName(),visitorApplyQueryVO.getState(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                list=visitorApplyMapper.selectByParam(visitorApplyQueryVO.getVisitorName(),visitorApplyQueryVO.getVisitorPhone(),visitorApplyQueryVO.getVisitorUserId(),visitorApplyQueryVO.getRegionId(),visitorApplyQueryVO.getRegionName(),visitorApplyQueryVO.getIntervieweeName(),visitorApplyQueryVO.getIntervieweePhone(),visitorApplyQueryVO.getIntervieweeId(),visitorApplyQueryVO.getVisitingTimeStart(),visitorApplyQueryVO.getVisitingTimeEnd(),visitorApplyQueryVO.getUpdateByName(),visitorApplyQueryVO.getState(),admin.getZoneId(),0);
                break;
            case ADMIN:
                list=visitorApplyMapper.selectByParam(visitorApplyQueryVO.getVisitorName(),visitorApplyQueryVO.getVisitorPhone(),visitorApplyQueryVO.getVisitorUserId(),visitorApplyQueryVO.getRegionId(),visitorApplyQueryVO.getRegionName(),visitorApplyQueryVO.getIntervieweeName(),visitorApplyQueryVO.getIntervieweePhone(),visitorApplyQueryVO.getIntervieweeId(),visitorApplyQueryVO.getVisitingTimeStart(),visitorApplyQueryVO.getVisitingTimeEnd(),visitorApplyQueryVO.getUpdateByName(),visitorApplyQueryVO.getState(),0,0);
                break;
            default:
                break;
        }

        List<VisitorApplyDTO> dtoList= ConvertUtils.sourceToTarget(list,VisitorApplyDTO.class);
        PageInfo<VisitorApplyDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
