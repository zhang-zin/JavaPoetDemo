package com.zj.arouter_api;

import android.app.Activity;

import androidx.collection.LruCache;

/**
 * @author zhangjin
 */
public class ParameterManager {

    private static ParameterManager instance;

    private LruCache<String, ParameterGet> cache;

    private static String FILE_SUFFIX_NAME = "$$Parameter";

    private ParameterManager() {
        cache = new LruCache<>(100);
    }

    public static ParameterManager getInstance() {
        if (instance == null) {
            synchronized (ParameterManager.class) {
                if (instance == null) {
                    instance = new ParameterManager();
                }
            }
        }
        return instance;
    }

    public void loadParameter(Activity activity) {
        String name = activity.getClass().getName();
        ParameterGet parameterGet = cache.get(name);
        if (parameterGet == null) {
            try {
                Class<?> aClass = Class.forName(name + FILE_SUFFIX_NAME);
                parameterGet = (ParameterGet) aClass.newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        parameterGet.getParameter(activity);
    }

}
