package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class UserResidentApplyQueryVO extends BaseQueryVO{

    private String userRealName;

    private String userPhone;

    private String regionName;

    private Integer regionId;

    private String updateByName;

    private Integer state;
}
