package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class VisitorApplyVerifyVO extends BaseQueryVO{
    private Integer applyId;
    private Integer verifyResult;
    private Integer waitHour;
    private Integer updateBy;
    private String reason;
}
