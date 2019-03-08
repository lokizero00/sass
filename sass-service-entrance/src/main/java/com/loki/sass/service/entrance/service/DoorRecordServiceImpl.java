package com.loki.sass.service.entrance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorRecordDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.DoorRecordQueryVO;
import com.loki.sass.domain.mapper.DoorRecordMapper;
import com.loki.sass.domain.po.DoorRecordPO;
import com.loki.sass.service.entrance.api.DoorRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Override
    public PageInfo<DoorRecordDTO> getDoorRecordListBySearch(DoorRecordQueryVO doorRecordQueryVO) throws BizException {
        if (!StringUtils.isEmpty(doorRecordQueryVO.getPage()) && !StringUtils.isEmpty(doorRecordQueryVO.getRows())) {
            PageHelper.startPage(doorRecordQueryVO.getPage(), doorRecordQueryVO.getRows());
        }
        List<DoorRecordPO> list=doorRecordMapper.selectByParam(doorRecordQueryVO.getDoorId(),doorRecordQueryVO.getDoorCode(),doorRecordQueryVO.getDoorName(),doorRecordQueryVO.getRegionId(),doorRecordQueryVO.getRegionName(),doorRecordQueryVO.getUserId(),doorRecordQueryVO.getUserPhone(),doorRecordQueryVO.getUserRealName(),doorRecordQueryVO.getCreateTimeStart(),doorRecordQueryVO.getCreateTimeEnd(),doorRecordQueryVO.getSuccess());
        List<DoorRecordDTO> dtoList= ConvertUtils.sourceToTarget(list,DoorRecordDTO.class);
        PageInfo<DoorRecordDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }
}
