package com.loki.sass.service.sysconfig.controller;

import com.loki.sass.common.dto.ResultDTO;
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
    public ResultDTO<Void> putIt(@RequestParam String code, @RequestParam String msg){
        ResultDTO<Void> result=new ResultDTO<>();
        tipCacheService.putIt(code,msg);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "v1/reload",method = RequestMethod.POST)
    public ResultDTO<Void> reload() throws BizException {
        ResultDTO<Void> result=new ResultDTO<>();
        tipCacheService.reload();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "v1/getTipMsg",method = RequestMethod.POST)
    public ResultDTO<String> getTipMsg(@RequestParam String code){
        ResultDTO<String> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(tipCacheService.getTipMsg(code));
        return result;
    }
}
