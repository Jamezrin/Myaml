package com.jaimemartz.myamltests.test4.objects;

import java.util.Map;

public class MapTypeInformation extends TypeInformation {
    private final Class<?> keyType, valueType;

    public MapTypeInformation(Class<?> keyType, Class<?> valueType) {
        super(Map.class);

        this.keyType = keyType;
        this.valueType = valueType;
    }

    public Class<?> getKeyType() {
        return keyType;
    }

    public Class<?> getValueType() {
        return valueType;
    }
}
