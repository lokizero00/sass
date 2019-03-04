package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum UserState {
    //禁用
    FORBIDDEN(0),
    //启用
    USING(1);

    private Integer value;

    UserState(Integer value) {
        this.value = value;
    }
    public static UserState fromValue(Integer value) {
        for (UserState userState : UserState.values()) {
            if (userState.getValue().equals(value)) {
                return userState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
