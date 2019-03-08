package com.loki.sass.service.entrance.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorRecordDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.DoorRecordQueryVO;

/**
 * created by lokizero00 on 2019-03-08
 */
public interface DoorRecordService {
    PageInfo<DoorRecordDTO> getDoorRecordListBySearch(DoorRecordQueryVO doorRecordQueryVO) throws BizException;
}
