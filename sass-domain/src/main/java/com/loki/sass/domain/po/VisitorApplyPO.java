package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-07
 */
@Data
public class VisitorApplyPO {
    private Integer id;

    private String visitorName;

    private String visitorPhone;

    private Integer visitorUserId;

    private String regionName;

    private Integer regionId;

    private Integer intervieweeId;

    private String intervieweePhone;

    private String intervieweeRealName;

    private String purpose;

    private Date createTime;

    private Date updateTime;

    private Date visitingTime;

    private Integer state;
}
