package com.github.lexya;

import com.github.lexya.minispring.MiniApplicationContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MiniApplicationContext miniApplicationContext = new MiniApplicationContext("com.github.lexya");
        HashMap<Class<?>, Object> beans = miniApplicationContext.getBeans();
        System.out.println(beans.size());
        for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {
            Class<?> clazz = entry.getKey();
            System.out.println("Класс " + clazz.getName() + " имеет поля:");
            Field[] fields =   clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    System.out.println("Имя поля: " + field.getName() + ", значение поля: " + field.get(entry.getValue()));
                }
                catch (Exception e) {
                     throw new RuntimeException(e);
                }
            }
            System.out.println();
        }


    }
}
