package com.jaimemartz.myaml.properties;

import com.jaimemartz.myaml.properties.types.*;

import java.util.*;
import java.util.function.Supplier;

public class PropertyHolder {
    private static Set<Property> props = new LinkedHashSet<>();

    public static <T> SimpleProperty<T> newProperty(String path, T defs) {
        SimpleProperty<T> property = new SimpleProperty<>(path, defs);
        props.add(property);
        return property;
    }

    public static <T> SimpleProperty<T> newProperty(String path, Supplier<T> defs) {
        return newProperty(path, defs.get());
    }

    public static <T> ListProperty<T> newListProperty(String path, List<T> defs) {
        ListProperty<T> property = new ListProperty<>(path, defs);
        props.add(property);
        return property;
    }

    public static <T> ListProperty<T> newListProperty(String path, T... defs) {
        return newListProperty(path, Arrays.asList(defs));
    }

    public static <T> ListProperty<T> newListProperty(String path, Supplier<List<T>> defs) {
        return newListProperty(path, defs.get());
    }

    public static <K, V> MapProperty<K, V> newMapProperty(String path, Map<K, V> defs) {
        MapProperty<K, V> property = new MapProperty<>(path, defs);
        props.add(property);
        return property;
    }

    public static <K, V> MapProperty<K, V> newMapProperty(String path, Supplier<Map<K, V>> defs) {
        return newMapProperty(path, defs.get());
    }

    public static Set<Property> getProps() {
        return props;
    }
}
