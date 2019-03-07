package com.loki.sass.service.zone.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.PropertyDTO;
import com.loki.sass.common.dto.ResultDTO;
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
    public ResultDTO<PageInfo<PropertyDTO>> getPropertyListSearch(@RequestParam String propertyQueryJson) throws BizException {
        ResultDTO<PageInfo<PropertyDTO>> result=new ResultDTO<>();
        PropertyQueryVO propertyQueryVO= JsonUtils.jsonToObject(propertyQueryJson,PropertyQueryVO.class);
        result.setSuccess(true);
        result.setModule(propertyService.getPropertyListSearch(propertyQueryVO));
        return result;
    }

    @RequestMapping(value = "v1/addProperty",method = RequestMethod.POST)
    public ResultDTO<Boolean> addProperty(@RequestParam String propertyRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        PropertyRequestVO propertyRequestVO= JsonUtils.jsonToObject(propertyRequestStr,PropertyRequestVO.class);
        result.setSuccess(propertyService.addProperty(propertyRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/editProperty",method = RequestMethod.POST)
    public ResultDTO<Boolean> editProperty(@RequestParam String propertyRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        PropertyRequestVO propertyRequestVO= JsonUtils.jsonToObject(propertyRequestStr,PropertyRequestVO.class);
        result.setSuccess(propertyService.editProperty(propertyRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/deleteProperty",method = RequestMethod.POST)
    public ResultDTO<Boolean> deleteProperty(@RequestParam Integer propertyId,@RequestParam Integer adminId) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(propertyService.deleteProperty(propertyId,adminId));
        return result;
    }
}
