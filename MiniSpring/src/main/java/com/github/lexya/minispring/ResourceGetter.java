package com.github.lexya.minispring;

import java.net.URL;
import java.util.List;

@FunctionalInterface
public interface ResourceGetter {
    List<Class<?>> execute(URL resource);
}
