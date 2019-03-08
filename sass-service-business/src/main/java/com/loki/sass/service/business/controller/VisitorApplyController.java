package com.loki.sass.service.business.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.VisitorApplyQueryVO;
import com.loki.sass.common.vo.VisitorApplyVerifyVO;
import com.loki.sass.service.business.api.VisitorApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.loki.sass.common.enums.VisitorApplyState.PASS;
import static com.loki.sass.common.enums.VisitorApplyState.REFUSE;

/**
 * created by lokizero00 on 2019-03-08
 */
@Slf4j
@RestController
@RequestMapping("/visitorApply")
public class VisitorApplyController {
    @Autowired
    VisitorApplyService visitorApplyService;

    @RequestMapping(value = "v1/getApplyListSearch",method = RequestMethod.POST)
    public ResultDTO<PageInfo<VisitorApplyDTO>> getApplyListSearch(@RequestParam String visitorApplyQueryJson) throws BizException {
        ResultDTO<PageInfo<VisitorApplyDTO>> result=new ResultDTO<>();
        VisitorApplyQueryVO visitorApplyQueryVO= JsonUtils.jsonToObject(visitorApplyQueryJson,VisitorApplyQueryVO.class);
        result.setSuccess(true);
        result.setModule(visitorApplyService.getApplyListSearch(visitorApplyQueryVO));
        return result;
    }

    @RequestMapping(value = "v1/applyVerify",method = RequestMethod.POST)
    public ResultDTO<Boolean> applyVerify(@RequestParam String visitorApplyVerifyStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        VisitorApplyVerifyVO visitorApplyVerifyVO= JsonUtils.jsonToObject(visitorApplyVerifyStr,VisitorApplyVerifyVO.class);
        boolean bs=false;

        if(visitorApplyVerifyVO.getVerifyResult().equals(PASS.getValue())){
            bs=visitorApplyService.applyPass(visitorApplyVerifyVO.getApplyId(),visitorApplyVerifyVO.getReason(),visitorApplyVerifyVO.getUpdateBy());
        }else if (visitorApplyVerifyVO.getVerifyResult().equals(REFUSE.getValue())){
            bs=visitorApplyService.applyRefuse(visitorApplyVerifyVO.getApplyId(),visitorApplyVerifyVO.getReason(),visitorApplyVerifyVO.getUpdateBy());
        }

        if(!bs){
            throw new BizException(VisitorResultCode.VISIT_VERIFY_ERROR);
        }
        result.setSuccess(true);
        return result;
    }
}
