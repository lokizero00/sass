package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum VisitorApplyState {
    //待审核
    VERIFY(1),
    //通过
    PASS(2),
    //拒绝
    REFUSE(3);

    private Integer value;

    VisitorApplyState(Integer value) {
        this.value = value;
    }
    public static VisitorApplyState fromValue(Integer value) {
        for (VisitorApplyState visitorApplyState : VisitorApplyState.values()) {
            if (visitorApplyState.getValue().equals(value)) {
                return visitorApplyState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
