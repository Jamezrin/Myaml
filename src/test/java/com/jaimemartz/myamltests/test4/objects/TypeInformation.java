package com.jaimemartz.myamltests.test4.objects;

public class TypeInformation {
    private final Class<?> type;

    public TypeInformation(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }
}
