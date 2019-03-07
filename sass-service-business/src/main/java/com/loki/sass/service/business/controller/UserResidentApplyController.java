package com.loki.sass.service.business.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserResidentApplyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;
import com.loki.sass.common.vo.UserResidentApplyVerifyVO;
import com.loki.sass.service.business.api.UserResidentApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.loki.sass.common.enums.UserResidentApplyState.*;

/**
 * created by lokizero00 on 2019-03-07
 */
@Slf4j
@RestController
@RequestMapping("/userResidentApply")
public class UserResidentApplyController {
    @Autowired
    UserResidentApplyService userResidentApplyService;

    @RequestMapping(value = "v1/getApplyListSearch",method = RequestMethod.POST)
    public ResultDTO<PageInfo<UserResidentApplyDTO>> getApplyListSearch(@RequestParam String userResidentApplyQueryJson) throws BizException {
        ResultDTO<PageInfo<UserResidentApplyDTO>> result=new ResultDTO<>();
        UserResidentApplyQueryVO userResidentApplyQueryVO= JsonUtils.jsonToObject(userResidentApplyQueryJson,UserResidentApplyQueryVO.class);
        result.setSuccess(true);
        result.setModule(userResidentApplyService.getApplyListSearch(userResidentApplyQueryVO));
        return result;
    }

    @RequestMapping(value = "v1/applyVerify",method = RequestMethod.POST)
    public ResultDTO<Boolean> applyVerify(@RequestParam String userResidentApplyVerifyStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        UserResidentApplyVerifyVO userResidentApplyQueryVO= JsonUtils.jsonToObject(userResidentApplyVerifyStr,UserResidentApplyVerifyVO.class);
        boolean bs=false;

        if(userResidentApplyQueryVO.getVerifyResult().equals(USING.getValue())){
            bs=userResidentApplyService.applyPass(userResidentApplyQueryVO.getApplyId(),userResidentApplyQueryVO.getUpdateBy());
        }else if (userResidentApplyQueryVO.getVerifyResult().equals(FORBIDDEN.getValue())){
            bs=userResidentApplyService.applyRefuse(userResidentApplyQueryVO.getApplyId(),userResidentApplyQueryVO.getUpdateBy());
        }

        if(!bs){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_ERROR);
        }
        result.setSuccess(true);
        return result;
    }
}
