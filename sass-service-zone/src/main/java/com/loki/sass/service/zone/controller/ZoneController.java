package com.loki.sass.service.zone.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
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
    public PageInfo<ZoneDTO> getZoneListSearch(@RequestParam String zoneQueryJson) throws BizException {
        ZoneQueryVO zoneQueryVO= JsonUtils.jsonToObject(zoneQueryJson,ZoneQueryVO.class);
        return zoneService.getZoneListSearch(zoneQueryVO);
    }
}