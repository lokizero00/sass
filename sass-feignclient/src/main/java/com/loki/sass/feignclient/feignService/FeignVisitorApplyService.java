package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-03-07
 */
@FeignClient("sass-service-business")
public interface FeignVisitorApplyService {

    @RequestMapping(value = "/visitorApply/v1/getApplyListSearch",method = RequestMethod.POST)
    ResultDTO<PageInfo<VisitorApplyDTO>> getApplyListSearch(@RequestParam("visitorApplyQueryJson") String visitorApplyQueryJson) throws BizException;

    @RequestMapping(value = "/visitorApply/v1/applyVerify",method = RequestMethod.POST)
    ResultDTO<Boolean> applyVerify(@RequestParam("visitorApplyVerifyStr") String visitorApplyVerifyStr) throws BizException ;
}
