package com.jaimemartz.myamltests.test2;

import com.jaimemartz.myaml.properties.PropertyHolder;
import com.jaimemartz.myaml.properties.annotations.CommentSupplier;
import com.jaimemartz.myaml.properties.annotations.Comments;
import com.jaimemartz.myaml.properties.types.ListProperty;
import com.jaimemartz.myaml.properties.types.MapProperty;
import com.jaimemartz.myaml.properties.types.Property;
import com.jaimemartz.myamltests.objects.TestBean;
import com.jaimemartz.myamltests.objects.TestEnum;

import java.util.*;

public class TestHolder extends PropertyHolder {
    @Comments(value = {"Hello, this is just a test", "This property is a string"})
    public static Property<String> TEST_STRING = newProperty("test-string", "Myaml");

    @Comments(value = {"Hello, this is just a test", "This property is a boolean"})
    public static Property<Boolean> TEST_BOOLEAN = newProperty("test-boolean", true);

    @Comments(value = {"Hello, this is just a test", "This property is a integer"})
    public static Property<Integer> TEST_INTEGER = newProperty("test-integer", 1000);

    @Comments(value = {"Hello, this is just a test", "This property is a enum"})
    public static Property<TestEnum> TEST_ENUM = newProperty("test-enum", TestEnum.COOL);

    @Comments(value = {"Hello, this is just a test", "This property is a bean"})
    public static Property<TestBean> TEST_BEAN = newProperty("test-bean", () -> {
        TestBean bean = new TestBean();
        bean.setName("Jaime");
        bean.setScore(9000);
        return bean;
    });

    @Comments(value = {"This is just a test", "This property is a list"})
    public static ListProperty<String> TEST_LIST = newListProperty("test-list", () -> {
        List<String> list = new ArrayList<>();
        list.add("string");
        return list;
    });

    @Comments(value = {"This is just a test", "This property is a map"})
    public static MapProperty<String, String> TEST_MAP = newMapProperty("test-map", () -> {
        Map<String, String> map = new HashMap<>();
        map.put("string", "other");
        return map;
    });

    @CommentSupplier
    public static Map<String, String> getComments() {
        Map<String, String> map = new HashMap<>();
        map.put("", "This is a supplied comment");
        return map;
    }
}
