package com.jaimemartz.myamltests.test3;

import com.jaimemartz.myamltests.objects.TestBean;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.*;

public class BeanTest {
    private Yaml yaml;

    @Before
    public void initTest() {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setAllowUnicode(true);

        Constructor constructor = new Constructor();
        constructor.addTypeDescription(new TypeDescription(TestBean.class, "!bean"));

        Representer representer = new Representer();
        representer.addClassTag(TestBean.class, new Tag("!bean"));

        yaml = new Yaml(constructor, representer, dumperOptions);
    }

    @Test
    public void testDumpWithTags() {
        Map<String, TestBean> map = new HashMap<>();

        TestBean test1 = new TestBean();
        test1.setName("Test1");
        test1.setScore(9000);
        map.put("hello", test1);

        TestBean test2 = new TestBean();
        test2.setName("Test2");
        test2.setScore(9000);
        map.put("world", test2);

        String text = yaml.dump(map);
        System.out.println(text);
    }

    @Test
    public void testLoadWithTags() {
        String text = ""
                + "world: !bean\n"
                + "  name: Test2\n"
                + "  score: 9000\n"
                + "hello: !bean\n"
                + "  name: Test1\n"
                + "  score: 9000";

        Map<String, TestBean> map = new HashMap<>();
        map.putAll(yaml.loadAs(text, map.getClass()));
        System.out.println(map);

        map.forEach((k, v) -> {
            System.out.println("Name: " + v.getName());
            System.out.println("Score: " + v.getScore());
        });
    }
}


//https://bitbucket.org/asomov/snakeyaml/wiki/Documentation#markdown-header-javabeans