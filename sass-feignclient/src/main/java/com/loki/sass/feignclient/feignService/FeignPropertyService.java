package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
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
public interface FeignPropertyService {
    @RequestMapping(method = RequestMethod.POST, value = "/property/v1/getPropertyListSearch")
    PageInfo<PropertyDTO> getPropertyListSearch(@RequestParam("propertyQueryJson") String propertyQueryJson) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/property/v1/addProperty")
    Boolean addProperty(@RequestParam("propertyRequestStr") String propertyRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/property/v1/editProperty")
    Boolean editProperty(@RequestParam("propertyRequestStr") String propertyRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/property/v1/deleteProperty")
    Boolean deleteProperty(@RequestParam("propertyId") Integer propertyId, @RequestParam("adminId") Integer adminId) throws BizException;
}
