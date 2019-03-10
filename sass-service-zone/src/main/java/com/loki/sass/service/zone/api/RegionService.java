package com.loki.sass.service.zone.api;

import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.RegionRequestVO;

import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
public interface RegionService {
    boolean addRegion(RegionRequestVO regionRequestVO) throws BizException;
    boolean editRegion(RegionRequestVO regionRequestVO) throws BizException;
    boolean deleteRegion(Integer regionId,Integer adminId) throws BizException;
    List<RegionDTO> getRegionListByParentId(Integer regionId) throws BizException;
    List<RegionDTO> getRootRegionList(Integer adminId) throws BizException;
//    List<RegionDTO> getRootRegionListByZoneId(Integer zoneId) throws BizException;
//    List<RegionDTO> getRootRegionListByPropertyId(Integer propertyId) throws BizException;

}
