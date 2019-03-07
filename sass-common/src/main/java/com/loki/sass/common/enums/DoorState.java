package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum DoorState {
    //禁用
    FORBIDDEN(-1),
    //启用
    USING(1);

    private Integer value;

    DoorState(Integer value) {
        this.value = value;
    }
    public static DoorState fromValue(Integer value) {
        for (DoorState doorState : DoorState.values()) {
            if (doorState.getValue().equals(value)) {
                return doorState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
