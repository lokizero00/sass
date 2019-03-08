package com.loki.sass.api.web.security.realm;

import com.loki.sass.common.dto.PermissionDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class ShiroAdmin implements Serializable {

    private Integer id;
    private String userName;
    private String realName;
    private String mobile;
    private String passwd;
    private String avatarUrl;
    private Set<String> roleSet;
    private Set<String> permissionSet;
    private Set<String> areaSet;
    private Integer isSuper;
    private Integer zoneId;
    private Integer propertyId;
    private Integer parentComFlag = 0;  //公司总部标识 1：总部，其他：非总部
    private List<PermissionDTO> menuResource;
    private String token;
}