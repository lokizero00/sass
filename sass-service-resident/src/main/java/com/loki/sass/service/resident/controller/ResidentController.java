package com.loki.sass.service.resident.controller;

import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.ResidentJoinRequestVO;
import com.loki.sass.domain.model.User;
import com.loki.sass.domain.model.UserAuth;
import com.loki.sass.domain.model.WechatAuth;
import com.loki.sass.service.resident.api.ResidentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by lokizero00 on 2019-02-27
 */
@Slf4j
@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    ResidentService residentService;

    @ApiOperation(value = "常驻用户注册", notes = "常驻用户注册")
    @RequestMapping(value = "/residentJoin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> residentJoin(@Valid @RequestBody ResidentJoinRequestVO residentJoinRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("入驻申请失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(residentJoinRequestVO.getMobile())
                || StringUtils.isEmpty(residentJoinRequestVO.getRealName())
                || StringUtils.isEmpty(residentJoinRequestVO.getOpenId())
                || residentJoinRequestVO.getRegionId()<=0){
            throw new BizException(ResidentResultCode.REGISTER_DATA_INVALID);
        }

        ResultDTO result = new ResultDTO();
        result.setSuccess(false);

        if(residentService.joinApply(residentJoinRequestVO)){
            result.setSuccess(true);
        }

        return result;
    }

}
