package com.loki.sass.service.zone.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;
import com.loki.sass.service.zone.api.ZoneService;
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
@RequestMapping("/zone")
public class ZoneController {
    @Autowired
    ZoneService zoneService;

    @RequestMapping(value = "v1/getZoneListSearch",method = RequestMethod.POST)
    public ResultDTO<?> getZoneListSearch(@RequestParam String zoneQueryJson) throws BizException {
        ResultDTO<PageInfo<ZoneDTO>> result=new ResultDTO<>();
        ZoneQueryVO zoneQueryVO= JsonUtils.jsonToObject(zoneQueryJson,ZoneQueryVO.class);
        result.setSuccess(true);
        result.setModule(zoneService.getZoneListSearch(zoneQueryVO));
        return result;
    }

    @RequestMapping(value = "v1/addZone",method = RequestMethod.POST)
    public ResultDTO<?> addZone(@RequestParam String zoneRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        ZoneRequestVO zoneRequestVO= JsonUtils.jsonToObject(zoneRequestStr,ZoneRequestVO.class);
        result.setSuccess(zoneService.addZone(zoneRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/editZone",method = RequestMethod.POST)
    public ResultDTO<?> editZone(@RequestParam String zoneRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        ZoneRequestVO zoneRequestVO= JsonUtils.jsonToObject(zoneRequestStr,ZoneRequestVO.class);
        result.setSuccess(zoneService.editZone(zoneRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/deleteZone",method = RequestMethod.POST)
    public ResultDTO<?> deleteZone(@RequestParam Integer zoneId,@RequestParam Integer adminId) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(zoneService.deleteZone(zoneId,adminId));
        return result;
    }
}
