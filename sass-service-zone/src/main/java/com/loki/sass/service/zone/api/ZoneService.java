package com.loki.sass.service.zone.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;

/**
 * created by lokizero00 on 2019-03-04
 */
public interface ZoneService {
    ResultDTO<?> addZone(ZoneRequestVO zoneRequestVO) throws BizException;
    ResultDTO<?> editZone(ZoneRequestVO zoneRequestVO) throws BizException;
    ResultDTO<?> deleteZone(Integer zoneId,Integer adminId) throws BizException;
    ResultDTO<?> getZoneListSearch(ZoneQueryVO zoneQueryVO) throws BizException;
}
