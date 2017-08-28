package com.jaimemartz.myaml.handle;

import java.io.File;
import java.util.Set;

public class ResourceReader {
    private final File file;

    public ResourceReader(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Set<ConstantProperty> read() {
        return null;
    }
}
