package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserResidentApplyDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-03-07
 */
@FeignClient("sass-service-business")
public interface FeignUserResidentApplyService {

    @RequestMapping(value = "/userResidentApply/v1/getApplyListSearch",method = RequestMethod.POST)
    ResultDTO<PageInfo<UserResidentApplyDTO>> getApplyListSearch(@RequestParam("userResidentApplyQueryJson") String userResidentApplyQueryJson) throws BizException;

    @RequestMapping(value = "/userResidentApply/v1/applyVerify",method = RequestMethod.POST)
    ResultDTO<Boolean> applyVerify(@RequestParam("userResidentApplyVerifyStr") String userResidentApplyVerifyStr) throws BizException ;
}
