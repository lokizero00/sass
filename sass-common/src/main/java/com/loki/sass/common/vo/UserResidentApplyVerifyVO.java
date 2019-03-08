package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class UserResidentApplyVerifyVO extends BaseQueryVO{
    private Integer applyId;
    private Integer verifyResult;
    private Integer updateBy;
}
