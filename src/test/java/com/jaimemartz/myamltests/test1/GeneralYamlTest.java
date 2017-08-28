package com.jaimemartz.myamltests.test1;

import com.jaimemartz.myamltests.objects.TestBean;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.util.*;

public class GeneralYamlTest {
    private Yaml yaml;

    @Before
    public void initTest() {
        DumperOptions dumperOptions = new DumperOptions();
        LoaderOptions loaderOptions = new LoaderOptions();
        Representer representer = new Representer();

        yaml = new Yaml(new Constructor(), representer, dumperOptions, loaderOptions);
    }

    @Test
    public void testMapWithStringArrays() {
        Map<String, String[]> map = new HashMap<>();
        map.put("test1", new String[] {"lol", "mate"});
        map.put("test2", new String[] {"watch", "this"});
        String text = yaml.dump(map);
        System.out.println(text);
    }

    @Test
    public void testMapWithStringLists() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("test1", Arrays.asList("lol", "mate"));
        map.put("test2", Arrays.asList("watch", "this"));
        String text = yaml.dump(map);
        System.out.println(text);
    }

    @Test
    public void testListsWithBeans() {
        List<TestBean> list = new ArrayList<>();

        TestBean test1 = new TestBean();
        test1.setName("Test1");
        test1.setScore(9000);
        list.add(test1);

        TestBean test2 = new TestBean();
        test2.setName("Test2");
        test2.setScore(9000);
        list.add(test2);

        String text = yaml.dump(list);
        System.out.println(text);
    }
}
