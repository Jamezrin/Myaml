package com.jaimemartz.myaml.resource;

import com.jaimemartz.myaml.properties.types.Property;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class ResourceHandle {
    private final File file;
    private final Set<ResourceProperty> props = new LinkedHashSet<>();

    public ResourceHandle(File file) {
        this.file = file;
    }

    public <T> void setValue(Property<T> property, T value) {

    }

    public <T> T getValue(Property<T> property) {
        return null;
    }

    public Set<ResourceProperty> getProps() {
        return null;
    }
}
