package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum UserResidentApplyState {
    //待审核
    VERIFY(0),
    //生效
    USING(1),
    //失效
    FORBIDDEN(2);

    private Integer value;

    UserResidentApplyState(Integer value) {
        this.value = value;
    }
    public static UserResidentApplyState fromValue(Integer value) {
        for (UserResidentApplyState userResidentApplyState : UserResidentApplyState.values()) {
            if (userResidentApplyState.getValue().equals(value)) {
                return userResidentApplyState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
