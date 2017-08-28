package com.jaimemartz.myamltests.test4.objects;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Map;
import java.util.Set;

public class TagLessRepresenter extends Representer {
    @Override
    protected MappingNode representJavaBean(Set<Property> properties, Object javaBean) {
        if (!classTags.containsKey(javaBean.getClass())) {
            addClassTag(javaBean.getClass(), Tag.MAP);
        }

        return super.representJavaBean(properties, javaBean);
    }
}
