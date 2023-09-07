package net.dengzixu.blivedanmaku.annotation;

import net.dengzixu.blivedanmaku.enums.CommandEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataResolver {
    CommandEnum[] command();
}
