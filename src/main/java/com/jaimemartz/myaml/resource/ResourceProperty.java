package com.jaimemartz.myaml.resource;

import com.jaimemartz.myaml.properties.types.Property;

public class ResourceProperty<T> {
    private final Property<T> property;
    private T value;

    public ResourceProperty(Property<T> property) {
        this.property = property;
    }

    public Property<T> getProperty() {
        return property;
    }
}
