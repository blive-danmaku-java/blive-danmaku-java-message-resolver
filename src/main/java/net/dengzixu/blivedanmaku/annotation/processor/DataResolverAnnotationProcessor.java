package net.dengzixu.blivedanmaku.annotation.processor;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataResolverAnnotationProcessor {
    private static final String PACKAGE_NAME = "net.dengzixu.blivedanmaku";

    public static final Map<CommandEnum, Class<?>> COMMAND_MAP = new HashMap<>();

    static {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<?>> annotatedClass = reflections.getTypesAnnotatedWith(DataResolver.class);

        annotatedClass.forEach(clazz -> {
            for (CommandEnum command : clazz.getAnnotation(DataResolver.class).command()) {
                COMMAND_MAP.put(command, clazz);
            }
        });
    }
}
