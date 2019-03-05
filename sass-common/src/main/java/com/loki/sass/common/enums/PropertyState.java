package com.loki.sass.common.enums;

/**
 * created by lokizero00 on 2019-02-17
 */
public enum PropertyState {
    //禁用
    FORBIDDEN(-1),
    //启用
    USING(1);

    private Integer value;

    PropertyState(Integer value) {
        this.value = value;
    }
    public static PropertyState fromValue(Integer value) {
        for (PropertyState propertyState : PropertyState.values()) {
            if (propertyState.getValue().equals(value)) {
                return propertyState;
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }
}
