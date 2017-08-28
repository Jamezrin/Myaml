package com.jaimemartz.myaml.properties.types;

import com.jaimemartz.myaml.resource.ResourceHandle;

import java.util.Objects;

public class Property<T> {
    private final String path;
    private final T defs;
    private ResourceHandle handle;

    public Property(String path, T defs) {
        this.path = path;
        this.defs = defs;
    }

    public T getValue(ResourceHandle handle) {
        Objects.requireNonNull(handle, "property is null");
        return handle.getValue(this);
    }

    protected T getValue() {
        Objects.requireNonNull(handle, "property is not possessed");
        return handle.getValue(this);
    }

    public void setValue(ResourceHandle handle, T value) {
        Objects.requireNonNull(handle, "property is null");
        handle.setValue(this, value);
    }

    protected void setValue(T value) {
        Objects.requireNonNull(handle, "property is not possessed");
        handle.setValue(this, value);
    }

    public void possessHandle(ResourceHandle handle) {
        //todo handle this with exceptions and shit
        this.handle = handle;
    }

    public String getPath() {
        return path;
    }

    public T getDefaults() {
        return defs;
    }
}
