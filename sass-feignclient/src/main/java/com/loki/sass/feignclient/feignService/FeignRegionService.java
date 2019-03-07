package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-zone")
public interface FeignRegionService {
    @RequestMapping(method = RequestMethod.POST,value = "/region/v1/addRegion")
    ResultDTO<Boolean> addRegion(@RequestParam("regionRequestStr") String regionRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/region/v1/editRegion")
    ResultDTO<Boolean> editRegion(@RequestParam("regionRequestStr") String regionRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/region/v1/deleteRegion")
    ResultDTO<Boolean> deleteRegion(@RequestParam("regionId") Integer regionId, @RequestParam("adminId") Integer adminId) throws BizException;

    @RequestMapping(value = "/region/v1/getRegionListByParentId",method = RequestMethod.POST)
    ResultDTO<List<RegionDTO>> getRegionListByParentId(@RequestParam("regionId") Integer regionId) throws BizException ;

    @RequestMapping(value = "/region/v1/getRootRegionListByZoneId",method = RequestMethod.POST)
    ResultDTO<List<RegionDTO>> getRootRegionListByZoneId(@RequestParam("zoneId") Integer zoneId) throws BizException;

    @RequestMapping(value = "/region/v1/getRootRegionListByPropertyId",method = RequestMethod.POST)
    ResultDTO<List<RegionDTO>> getRootRegionListByPropertyId(@RequestParam("propertyId") Integer propertyId) throws BizException;
}
