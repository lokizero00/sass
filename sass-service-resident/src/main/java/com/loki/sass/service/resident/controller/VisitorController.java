package com.loki.sass.service.resident.controller;

import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.VisitApplyRequestVO;
import com.loki.sass.service.resident.api.VisitorService;
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
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @ApiOperation(value = "访客来访申请", notes = "访客来访申请")
    @RequestMapping(value = "/visitApply", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> visitApply(@Valid @RequestBody VisitApplyRequestVO visitApplyRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("来访申请失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(visitApplyRequestVO.getMobile())
                || StringUtils.isEmpty(visitApplyRequestVO.getRealName())
                || StringUtils.isEmpty(visitApplyRequestVO.getOpenId())
                || visitApplyRequestVO.getRegionId()<=0
                || StringUtils.isEmpty(visitApplyRequestVO.getIntervieweeMobile())
                || null==visitApplyRequestVO.getVisitingTime()){
            throw new BizException(VisitorResultCode.VISIT_DATA_INVALID);
        }

        ResultDTO result = new ResultDTO();
        result.setSuccess(false);

        if(visitorService.visitApply(visitApplyRequestVO)){
            result.setSuccess(true);
        }

        return result;
    }

}
