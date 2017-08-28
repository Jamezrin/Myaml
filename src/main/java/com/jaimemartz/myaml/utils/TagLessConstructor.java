package com.jaimemartz.myaml.utils;

import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TagLessConstructor extends Constructor {
    private final Map<String, TypeInformation> types = new HashMap<>();
    private MappingNode theRoot = null;

    public TagLessConstructor() {
        yamlConstructors.put(Tag.MAP, new MapConstructor());
    }

    private class MapConstructor extends Constructor.ConstructYamlMap {
        @Override
        public Object construct(Node node) {
            MappingNode mappingNode = (MappingNode) node;
            if (theRoot == null) {
                theRoot = mappingNode;
            }

            for (NodeTuple tuple : mappingNode.getValue()) {
                Node keyNode = tuple.getKeyNode();
                Node valueNode = tuple.getValueNode();

                if (keyNode instanceof ScalarNode) {
                    //TypeInformation information = types.get(((ScalarNode) keyNode).getValue()); //calculate paths from this to parents (from root to this node)
                    TypeInformation information = types.get(calculatePath((ScalarNode) keyNode)); //calculate paths from this to parents (from root to this node)

                    if (information != null) {
                        if (valueNode instanceof MappingNode) {
                            if (information instanceof MapTypeInformation) {
                                ((MappingNode) valueNode).setTypes(
                                        ((MapTypeInformation) information).getKeyType(),
                                        ((MapTypeInformation) information).getValueType());
                            } else {
                                valueNode.setType(information.getType());
                            }
                        } else if (valueNode instanceof SequenceNode) {
                            ((SequenceNode) valueNode).setListType(information.getType());
                        }
                    }
                }
            }

            return super.construct(node);
        }
    }

    //TODO calculate parent by getting root to the provided node
    private String calculatePath(ScalarNode node) {
        StringBuilder builder = new StringBuilder();

        for (ScalarNode other : getNodes(theRoot, node)) {
            if (builder.length() != 0) {
                builder.append(".");
            }

            builder.append(other.getValue());
        }

        return builder.toString();
    }

    private List<ScalarNode> getNodes(MappingNode node, ScalarNode target) {
        List<ScalarNode> list = new ArrayList<>();
        return list;
    }

    public void setPropertyType(String path, Class type) {
        types.put(path, new TypeInformation(type));
    }

    public void setListPropertyType(String path, Class type) {
        types.put(path, new TypeInformation(type));
    }

    public void setMapPropertyTypes(String path, Class keyType, Class valueType) {
        types.put(path, new MapTypeInformation(keyType, valueType));
    }
}
