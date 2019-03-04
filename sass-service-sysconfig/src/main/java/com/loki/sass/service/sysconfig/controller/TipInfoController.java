package com.loki.sass.service.sysconfig.controller;

import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.sysconfig.util.TipCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-15
 */

@Slf4j
@RestController
@RequestMapping("/tipInfo")
public class TipInfoController {
    @Autowired
    TipCacheService tipCacheService;

    @RequestMapping(value = "v1/putIt",method = RequestMethod.POST)
    public void putIt(@RequestParam String code,@RequestParam String msg){
        tipCacheService.putIt(code,msg);
    }

    @RequestMapping(value = "v1/reload",method = RequestMethod.POST)
    public void reload() throws BizException {
        tipCacheService.reload();
    }

    @RequestMapping(value = "v1/getTipMsg",method = RequestMethod.POST)
    public String getTipMsg(@RequestParam String code){
        return tipCacheService.getTipMsg(code);
    }
}
