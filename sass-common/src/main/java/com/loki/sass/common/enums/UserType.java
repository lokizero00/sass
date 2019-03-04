package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum UserType {
    //用户
    RESIDENT(2),
    //访客
    VISITOR(3);

    private Integer value;

    UserType(Integer value) {
        this.value = value;
    }
    public static UserType fromValue(Integer value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue().equals(value)) {
                return userType;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
