package com.jaimemartz.myamltests.test4.objects;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.*;

import java.util.HashMap;
import java.util.Map;

public class TagLessConstructor extends Constructor {
    private final Map<String, TypeInformation> types = new HashMap<>();

    public TagLessConstructor() {
        yamlConstructors.put(Tag.MAP, new MapConstructor());
    }

    public void putMapPropertyType(String path, Class keyType, Class valueType) {
        MapTypeInformation info = new MapTypeInformation(keyType, valueType);
        types.put(path, info);
    }

    public void putListPropertyType(String path, Class type) {
        TypeInformation info = new TypeInformation(type);
        types.put(path, info);
    }

    public void putPropertyType(String path, Class type) {
        TypeInformation info = new TypeInformation(type);
        types.put(path, info);
    }

    private class MapConstructor extends Constructor.ConstructYamlMap {
        @Override
        public Object construct(Node node) {
            MappingNode mappingNode = (MappingNode) node;
            for (NodeTuple tuple : mappingNode.getValue()) {
                Node keyNode = tuple.getKeyNode();
                if (keyNode instanceof ScalarNode) {
                    TypeInformation information = types.get(((ScalarNode) keyNode).getValue());
                    if (information != null) {
                        Node valueNode = tuple.getValueNode();
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
}
