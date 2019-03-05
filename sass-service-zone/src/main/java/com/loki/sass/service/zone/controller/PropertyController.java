package com.loki.sass.service.zone.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PropertyQueryVO;
import com.loki.sass.common.vo.PropertyRequestVO;
import com.loki.sass.service.zone.api.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-17
 */

@Slf4j
@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @RequestMapping(value = "v1/getPropertyListSearch",method = RequestMethod.POST)
    public PageInfo<PropertyDTO> getPropertyListSearch(@RequestParam String propertyQueryJson) throws BizException {
        PropertyQueryVO propertyQueryVO= JsonUtils.jsonToObject(propertyQueryJson,PropertyQueryVO.class);
        return propertyService.getPropertyListSearch(propertyQueryVO);
    }

    @RequestMapping(value = "v1/addProperty",method = RequestMethod.POST)
    public Boolean addProperty(@RequestParam String propertyRequestStr) throws BizException {
        PropertyRequestVO propertyRequestVO= JsonUtils.jsonToObject(propertyRequestStr,PropertyRequestVO.class);
        return propertyService.addProperty(propertyRequestVO);
    }

    @RequestMapping(value = "v1/editProperty",method = RequestMethod.POST)
    public Boolean editProperty(@RequestParam String propertyRequestStr) throws BizException {
        PropertyRequestVO propertyRequestVO= JsonUtils.jsonToObject(propertyRequestStr,PropertyRequestVO.class);
        return propertyService.editProperty(propertyRequestVO);
    }

    @RequestMapping(value = "v1/deleteProperty",method = RequestMethod.POST)
    public Boolean deleteProperty(@RequestParam Integer propertyId,@RequestParam Integer adminId) throws BizException {
        return propertyService.deleteProperty(propertyId,adminId);
    }
}
