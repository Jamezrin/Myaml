package com.jaimemartz.myaml.properties.types;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapProperty<K, V> extends Property<Map<K, V>> implements Map<K, V> {
    public MapProperty(String path, Map<K, V> defs) {
        super(path, defs);
    }

    @Override
    public int size() {
        return getValue().size();
    }

    @Override
    public boolean isEmpty() {
        return getValue().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return getValue().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getValue().containsValue(value);
    }

    @Override
    public V get(Object key) {
        return getValue().get(key);
    }

    @Override
    public V put(K key, V value) {
        return getValue().put(key, value);
    }

    @Override
    public V remove(Object key) {
        return getValue().remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        getValue().putAll(map);
    }

    @Override
    public void clear() {
        getValue().clear();
    }

    @Override
    public Set<K> keySet() {
        return getValue().keySet();
    }

    @Override
    public Collection<V> values() {
        return getValue().values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return getValue().entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return getValue().getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        getValue().forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        getValue().replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return getValue().putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return getValue().remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return getValue().replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return getValue().replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return getValue().computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return getValue().computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return getValue().compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return getValue().merge(key, value, remappingFunction);
    }
}
