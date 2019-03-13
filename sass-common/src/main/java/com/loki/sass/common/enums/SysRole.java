package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum SysRole {

    //未知
    UNKNOWN("unknown"),
    //超级管理员
    ADMIN("admin"),
    //小区管理员
    ZONE("zone"),
    //物业管理员
    PROPERTY("property");

    private String value;

    SysRole(String value) {
        this.value = value;
    }
    public static SysRole fromValue(Integer value) {
        for (SysRole sysRole : SysRole.values()) {
            if (sysRole.getValue().equals(value)) {
                return sysRole;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
