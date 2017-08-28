package com.jaimemartz.myaml.properties.types;

public class SimpleProperty<T> extends Property<T> {
    public SimpleProperty(String path, T defs) {
        super(path, defs);
    }

    public T get() {
        return getValue();
    }

    public void set(T value) {
        setValue(value);
    }
}
