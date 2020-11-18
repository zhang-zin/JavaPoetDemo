package com.zj.javapoetdemo_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangjin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ARouter {

    String path();

    String group() default "";
}
