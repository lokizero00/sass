package com.loki.sass.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class VisitorApplyQueryVO extends BaseQueryVO{

    private String visitorName;

    private String visitorPhone;

    private Integer visitorUserId;

    private Integer regionId;

    private String regionName;

    private String intervieweeName;

    private String intervieweePhone;

    private Integer intervieweeId;

    private String visitingTimeStart;

    private String visitingTimeEnd;

    private String updateByName;

    private Integer state;

    private Integer adminId;
}
