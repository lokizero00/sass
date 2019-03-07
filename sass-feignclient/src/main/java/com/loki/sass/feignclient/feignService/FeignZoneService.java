package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-zone")
public interface FeignZoneService {
    @RequestMapping(method = RequestMethod.POST, value = "/zone/v1/getZoneListSearch")
    ResultDTO<?> getZoneListSearch(@RequestParam("zoneQueryJson") String zoneQueryJson) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/zone/v1/addZone")
    ResultDTO<?> addZone(@RequestParam("zoneRequestStr") String zoneRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/zone/v1/editZone")
    ResultDTO<?> editZone(@RequestParam("zoneRequestStr") String zoneRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/zone/v1/deleteZone")
    ResultDTO<?> deleteZone(@RequestParam("zoneId") Integer zoneId,@RequestParam("adminId") Integer adminId) throws BizException;
}
