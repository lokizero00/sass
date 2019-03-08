package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-07
 */
@Data
public class VisitorApplyDTO {
    private Integer id;

    private String visitorName;

    private String visitorPhone;

    private Integer visitorUserId;

    private String regionName;

    private Integer regionId;

    private Integer intervieweeId;

    private String intervieweePhone;

    private String intervieweeName;

    private String purpose;

    private Date createTime;

    private Date updateTime;

    private Date visitingTime;

    private Integer updateBy;

    private String updateByName;

    private Integer state;

    private String reason;
}
