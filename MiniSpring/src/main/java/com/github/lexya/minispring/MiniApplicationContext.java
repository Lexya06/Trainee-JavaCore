package com.github.lexya.minispring;

import com.github.lexya.annotations.Autowired;
import com.github.lexya.annotations.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class MiniApplicationContext {
    private final String packageName;
    private final Map<Class<?>, Object> beans;
    private final Map<String, ResourceGetter> supportedResources = Map.ofEntries(Map.entry("file", this::prepareFileResource));

    public MiniApplicationContext(String packageName) {
        this.packageName = packageName;
        this.beans = new HashMap<>();
        initializeContext();
    }

    private void initializeContext() {
        List<Class<?>> allClasses = getClassesFromPackage();
        List<Class<?>> componentClasses = findComponentsClasses(allClasses);
        createBeansWithDependencies(componentClasses);
        initializingBeans();
    }

    private List<Class<?>> findComponentsClasses(List<Class<?>> classes) {
        return classes.stream().filter(c -> c.isAnnotationPresent(Component.class)).collect(Collectors.toList());
    }

    private void createBeansWithDependencies(List<Class<?>> components) {
        HashMap<Class<?>, Object> founded = new HashMap<>();
        components.forEach(c ->{
            try {
                Object instance = c.getDeclaredConstructor().newInstance();
                founded.put(c, instance);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        for (Map.Entry<Class<?>, Object> entry : founded.entrySet()) {
            Class<?> key = entry.getKey();
            Object value = entry.getValue();
            injectDependency(entry.getKey(), value, founded);
            beans.put(key, value);
        }
    }

    public <T> T getBean(Class<T> beanClass) {
        Object bean = beans.get(beanClass);
        if (beanClass.isInstance(bean)) {
            return beanClass.cast(bean);
        }
        else
            throw new IllegalArgumentException(beanClass.getName() + " is not a bean");
    }

    public HashMap<Class<?>, Object> getBeans() {
        return new HashMap<>(beans);
    }

    private void injectDependency(Class<?> entry, Object bean, HashMap<Class<?>, Object> dependencies) {
        Field[] fields = entry.getDeclaredFields();
        for (Field field : fields) {
            // try to find class type
            Class<?> type = field.getType();
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = dependencies.get(type);
                if (dependency == null) {
                    throw new RuntimeException("dependency object is null for type " + type);
                }
                field.setAccessible(true);
                try {
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void initializingBeans(){
        for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {
            Class<?> key = entry.getKey();
            Object bean = entry.getValue();
            if (bean instanceof InitializingBean) {
                try {
                    ((InitializingBean) bean).afterPropertiesSet();
                }
                catch (Exception e) {
                    throw new RuntimeException("error in afterPropertiesSet for bean with class" + key.getName());
                }
            }
        }
    }

    private List<Class<?>> getClassesFromPackage() {
        List<Class<?>> classes = new ArrayList<>();
        // get url
        String urlPath = packageName.replace('.', '/');

        // get resource by url
        URL resource = Thread.currentThread().getContextClassLoader().getResource(urlPath);
        if (resource == null) {
            return classes;
        }

        String protocol = resource.getProtocol();
        if (supportedResources.get(protocol)  == null) {
            throw new RuntimeException("Resource " + protocol + " is not supported");
        }
        return supportedResources.get(protocol).execute(resource);


    }

    private List<Class<?>> prepareFileResource(URL resource){
        List<Class<?>> classes = new ArrayList<>();
        try {
            File directory = (Paths.get(resource.toURI())).toFile();
            if (!directory.exists()) {
                return classes;
            }
            return fileProcessing(directory, packageName, classes);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private List<Class<?>> fileProcessing(File directory, String packageName, List<Class<?>> classes) {
        File[] files;
        if (directory.isDirectory()){
            files = directory.listFiles();
        }
        else if (directory.isFile()) {
            files = new File[]{directory};
        }
        else
            return classes;
        if (files == null)
            return classes;
        for (File file : files) {
            if (file.isDirectory()) {
                fileProcessing(file, packageName + "." + file.getName(), classes);
            }
            else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - ".class".length());
                try {
                    classes.add(Class.forName(className));
                }
                catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return classes;


    }
}






