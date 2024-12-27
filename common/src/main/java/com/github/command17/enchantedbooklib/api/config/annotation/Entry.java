package com.github.command17.enchantedbooklib.api.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entry {
    String name();
    String category() default "";
    String comment() default "";

    boolean synced() default false;
}
