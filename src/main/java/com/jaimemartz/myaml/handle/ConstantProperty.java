package com.jaimemartz.myaml.handle;

public class ConstantProperty<T> {
    private final String path;
    private final T value;

    public ConstantProperty(String path, T value) {
        this.path = path;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public T getValue() {
        return value;
    }
}
