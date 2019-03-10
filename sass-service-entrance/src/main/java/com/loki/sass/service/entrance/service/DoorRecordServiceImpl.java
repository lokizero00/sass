package com.loki.sass.service.entrance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.DoorRecordDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.enums.SysRole;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.DoorRecordQueryVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.mapper.DoorRecordMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.po.DoorRecordPO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.entrance.api.DoorRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by lokizero00 on 2019-03-08
 */
@Slf4j
@Service
@Transactional
public class DoorRecordServiceImpl implements DoorRecordService {
    @Autowired
    DoorRecordMapper doorRecordMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    FeignRoleService feignRoleService;

    @Override
    public PageInfo<DoorRecordDTO> getDoorRecordListBySearch(DoorRecordQueryVO doorRecordQueryVO) throws BizException {
        Admin admin=adminMapper.selectByPrimaryKey(doorRecordQueryVO.getAdminId());
        if(null==admin){
            throw new BizException(AdminResultCode.ADMIN_NOT_EXIST);
        }

        ResultDTO<SysRole> roleTypeResult=feignRoleService.getDataIsolationLevel(admin.getId());
        if(!roleTypeResult.isSuccess()){
            throw new BizException(RoleResultCode.ROLE_DATA_ISOLATION_LEVEL_ERROR);
        }
        SysRole roleType=roleTypeResult.getModule();

        if (!StringUtils.isEmpty(doorRecordQueryVO.getPage()) && !StringUtils.isEmpty(doorRecordQueryVO.getRows())) {
            PageHelper.startPage(doorRecordQueryVO.getPage(), doorRecordQueryVO.getRows());
        }
        List<DoorRecordPO> list=new ArrayList<>();
        switch(roleType){
            case PROPERTY:
                list=doorRecordMapper.selectByParam(doorRecordQueryVO.getDoorId(),doorRecordQueryVO.getDoorCode(),doorRecordQueryVO.getDoorName(),doorRecordQueryVO.getRegionId(),doorRecordQueryVO.getRegionName(),doorRecordQueryVO.getUserId(),doorRecordQueryVO.getUserPhone(),doorRecordQueryVO.getUserRealName(),doorRecordQueryVO.getCreateTimeStart(),doorRecordQueryVO.getCreateTimeEnd(),doorRecordQueryVO.getSuccess(),admin.getZoneId(),admin.getPropertyId());
                break;
            case ZONE:
                list=doorRecordMapper.selectByParam(doorRecordQueryVO.getDoorId(),doorRecordQueryVO.getDoorCode(),doorRecordQueryVO.getDoorName(),doorRecordQueryVO.getRegionId(),doorRecordQueryVO.getRegionName(),doorRecordQueryVO.getUserId(),doorRecordQueryVO.getUserPhone(),doorRecordQueryVO.getUserRealName(),doorRecordQueryVO.getCreateTimeStart(),doorRecordQueryVO.getCreateTimeEnd(),doorRecordQueryVO.getSuccess(),admin.getZoneId(),0);
                break;
            case ADMIN:
                list=doorRecordMapper.selectByParam(doorRecordQueryVO.getDoorId(),doorRecordQueryVO.getDoorCode(),doorRecordQueryVO.getDoorName(),doorRecordQueryVO.getRegionId(),doorRecordQueryVO.getRegionName(),doorRecordQueryVO.getUserId(),doorRecordQueryVO.getUserPhone(),doorRecordQueryVO.getUserRealName(),doorRecordQueryVO.getCreateTimeStart(),doorRecordQueryVO.getCreateTimeEnd(),doorRecordQueryVO.getSuccess(),0,0);
                break;
            default:
                break;
        }

        List<DoorRecordDTO> dtoList= ConvertUtils.sourceToTarget(list,DoorRecordDTO.class);
        PageInfo<DoorRecordDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
