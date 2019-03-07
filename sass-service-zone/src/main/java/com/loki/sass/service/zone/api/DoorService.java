package com.loki.sass.service.zone.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.DoorQueryVO;
import com.loki.sass.common.vo.DoorRequestVO;

/**
 * created by lokizero00 on 2019-03-05
 */
public interface DoorService {
    boolean addDoor(DoorRequestVO doorRequestVO) throws BizException;
    boolean editDoor(DoorRequestVO doorRequestVO) throws BizException;
    boolean deleteDoor(Integer doorId,Integer adminId) throws BizException;
    PageInfo<DoorDTO> getDoorListSearch(DoorQueryVO doorQueryVO) throws BizException;
}
