package com.zj.javapoetdemo_compiler.utils;

import com.zj.javapoetdemo_annotations.bean.RouterBean;

import java.util.Collection;
import java.util.List;

public class ProcessorUtils {

    public static boolean isEmptyString(String e) {
        return e == null || e.isEmpty();
    }

    public static boolean  isEmptyList(Collection<?> c) {
        return c == null || c.isEmpty();
    }
}
