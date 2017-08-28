package com.jaimemartz.myamltests.test4;

import com.jaimemartz.myamltests.objects.TestBean;
import com.jaimemartz.myamltests.test4.objects.TagLessConstructor;
import com.jaimemartz.myamltests.test4.objects.TagLessRepresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PracticalUseTest {
    private Map<String, Object> map = new HashMap<>();
    private String output = null;
    private Yaml yaml = null;

    @Before
    public void setup() {
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

        TagLessConstructor constructor = new TagLessConstructor();
        constructor.putMapPropertyType("beans-map", String.class, TestBean.class);
        constructor.putListPropertyType("beans-list", TestBean.class);
        constructor.putPropertyType("single-bean", TestBean.class);

        TagLessRepresenter representer = new TagLessRepresenter();
        yaml = new Yaml(constructor, representer);
        output = yaml.dump(map);
    }

    @Test
    public void softLoad() {
        System.out.println("Soft load:");
        Map<String, Object> loaded = (Map<String, Object>) yaml.load(output);
        System.out.println(loaded);
    }

    @Test
    public void hardLoad() {
        System.out.println("Hard load:");
        Map<String, Object> loaded = (Map<String, Object>) yaml.load(output);

        Map<String, TestBean> beanMap = (Map<String, TestBean>) loaded.get("beans-map");
        beanMap.forEach((k, v) -> {
            System.out.println(String.format("Key: %s, Value: %s", k, v.toString()));
        });

        List<TestBean> beanList = (List<TestBean>) loaded.get("beans-list");
        beanList.forEach(v -> {
            System.out.println(String.format("Value: %s", v.toString()));
        });

        TestBean singleBean = (TestBean) loaded.get("single-bean");
        System.out.println(String.format("Value: %s", singleBean.toString()));
    }
}
