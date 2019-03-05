package com.loki.sass.service.zone.controller;

import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.service.zone.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    @RequestMapping(value = "v1/addRegion",method = RequestMethod.POST)
    public Boolean addRegion(@RequestParam String regionRequestStr) throws BizException {
        RegionRequestVO regionRequestVO= JsonUtils.jsonToObject(regionRequestStr,RegionRequestVO.class);
        return regionService.addRegion(regionRequestVO);
    }

    @RequestMapping(value = "v1/editRegion",method = RequestMethod.POST)
    public Boolean editRegion(@RequestParam String regionRequestStr) throws BizException {
        RegionRequestVO regionRequestVO= JsonUtils.jsonToObject(regionRequestStr,RegionRequestVO.class);
        return regionService.editRegion(regionRequestVO);
    }

    @RequestMapping(value = "v1/deleteRegion",method = RequestMethod.POST)
    public Boolean deleteRegion(@RequestParam Integer regionId,@RequestParam Integer adminId) throws BizException {
        return regionService.deleteRegion(regionId,adminId);
    }

    @RequestMapping(value = "v1/getRegionListByParentId",method = RequestMethod.POST)
    public List<RegionDTO> getRegionListByParentId(@RequestParam Integer regionId) throws BizException {
        return regionService.getRegionListByParentId(regionId);
    }

    @RequestMapping(value = "v1/getRootRegionListByZoneId",method = RequestMethod.POST)
    public List<RegionDTO> getRootRegionListByZoneId(@RequestParam Integer zoneId) throws BizException {
        return regionService.getRootRegionListByZoneId(zoneId);
    }

    @RequestMapping(value = "v1/getRootRegionListByPropertyId",method = RequestMethod.POST)
    public List<RegionDTO> getRootRegionListByPropertyId(@RequestParam Integer propertyId) throws BizException {
        return regionService.getRootRegionListByPropertyId(propertyId);
    }
}
