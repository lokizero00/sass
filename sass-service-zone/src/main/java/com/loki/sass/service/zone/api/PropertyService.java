package com.loki.sass.service.zone.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.PropertyQueryVO;
import com.loki.sass.common.vo.PropertyRequestVO;

/**
 * created by lokizero00 on 2019-03-05
 */
public interface PropertyService {
    boolean addProperty(PropertyRequestVO propertyRequestVO) throws BizException;
    boolean editProperty(PropertyRequestVO propertyRequestVO) throws BizException;
    boolean deleteProperty(Integer propertyId,Integer adminId) throws BizException;
    PageInfo<PropertyDTO> getPropertyListSearch(PropertyQueryVO propertyQueryVO) throws BizException;
}
