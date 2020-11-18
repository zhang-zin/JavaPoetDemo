package com.zj.arouter_api;

import com.zj.javapoetdemo_annotations.bean.RouterBean;

import java.util.Map;

public interface ARouterPath {

    /**
     * path和RouterBean 信息对应
     *
     * @return map
     */
    Map<String, RouterBean> getPathMap();
}
