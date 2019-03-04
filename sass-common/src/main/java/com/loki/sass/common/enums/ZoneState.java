package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum ZoneState {
    //禁用
    FORBIDDEN(-1),
    //启用
    USING(1);

    private Integer value;

    ZoneState(Integer value) {
        this.value = value;
    }
    public static ZoneState fromValue(Integer value) {
        for (ZoneState zoneState : ZoneState.values()) {
            if (zoneState.getValue().equals(value)) {
                return zoneState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
