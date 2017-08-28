package com.jaimemartz.myamltests.test4;

import com.jaimemartz.myamltests.objects.TestBean;
import com.jaimemartz.myaml.utils.TagLessConstructor;
import com.jaimemartz.myaml.utils.TagLessRepresenter;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class PracticalUseTest {
    private String output = null;
    private Yaml yaml = null;

    @Before
    public void setup() {
        Map<String, Object> map = new HashMap<>();

        //Map
        Map<String, Object> beanMap = new HashMap<>();

        TestBean test1 = new TestBean();
        test1.setName("Test1");
        test1.setScore(9000);
        beanMap.put("hello", test1);

        TestBean test2 = new TestBean();
        test2.setName("Test2");
        test2.setScore(9000);
        beanMap.put("world", test2);

        //List
        List<TestBean> beanList = new ArrayList<>();

        TestBean test3 = new TestBean();
        test3.setName("Test3");
        test3.setScore(9000);
        beanList.add(test3);

        TestBean test4 = new TestBean();
        test4.setName("Test4");
        test4.setScore(9000);
        beanList.add(test4);

        //Single
        TestBean singleBean = new TestBean();
        singleBean.setName("Test5");
        singleBean.setScore(9000);

        map.put("beans-map", beanMap);
        map.put("beans-list", beanList);
        map.put("single-bean", singleBean);

        //Preparation for loading and initial dump
        TagLessConstructor constructor = new TagLessConstructor();
        constructor.setMapPropertyTypes("beans-map", String.class, TestBean.class);
        constructor.setListPropertyType("beans-list", TestBean.class);
        constructor.setPropertyType("single-bean", TestBean.class);

        yaml = new Yaml(constructor, new TagLessRepresenter());

        Map<String, Object> other = new HashMap<>();
        other.put("stuff", map);
        output = yaml.dump(other);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void softLoad() {
        System.out.println("Soft load:");
        Map<String, Object> loaded = (Map<String, Object>) yaml.load(output);
        System.out.println(loaded);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void hardLoad() {
        System.out.println("Hard load:");
        Map<String, Object> loaded = (Map<String, Object>) yaml.load(output);

        loaded = (Map<String, Object>) loaded.get("stuff");

        Map<String, TestBean> beanMap = (Map<String, TestBean>) loaded.get("beans-map");
        assertNotNull(beanMap);

        List<TestBean> beanList = (List<TestBean>) loaded.get("beans-list");
        assertNotNull(beanList);

        TestBean singleBean = (TestBean) loaded.get("single-bean");
        assertNotNull(singleBean);
    }
}
