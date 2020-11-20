package com.zj.javapoetdemo_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangjin
 * 字段参数注解
 */
@Target(ElementType.FIELD) // 作用在字段上
@Retention(RetentionPolicy.CLASS) // 运行在编译期
public @interface Parameter {

    String name() default "";

}
