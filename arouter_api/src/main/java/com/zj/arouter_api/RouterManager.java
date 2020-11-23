package com.zj.arouter_api;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.collection.LruCache;

import com.zj.javapoetdemo_annotations.bean.RouterBean;

import java.util.regex.Pattern;

/**
 * 路由管理器
 *
 * @author zhangjin
 */
public class RouterManager {

    private String FILE_SUFFIX_NAME = "ARouter$$Group$$";

    private String group;
    private String path;

    private static RouterManager instance;
    private LruCache<String, ARouterGroup> groupLruCache;
    private LruCache<String, ARouterPath> pathLruCache;

    private RouterManager() {
        groupLruCache = new LruCache<>(100);
        pathLruCache = new LruCache<>(100);
    }

    public static RouterManager getInstance() {
        if (instance == null) {
            synchronized (RouterManager.class) {
                if (instance == null) {
                    instance = new RouterManager();
                }
            }
        }
        return instance;
    }

    public BundleManager build(String path) {

        if (TextUtils.isEmpty(path)) {
            throw new IllegalArgumentException("路径名不能为空");
        }

        String regex = "^/\\w+/\\w+$";
        boolean matches = Pattern.matches(regex, path);
        if (!matches) {
            throw new IllegalArgumentException("路径名不符合规范，正确写法：如 /order/Order_MainActivity");
        }

        String substring = path.substring(1, path.indexOf("/", 1));
        this.path = path;
        this.group = substring;
        return new BundleManager();
    }

    /**
     * ARouter$$Group$$app
     *
     * @param context
     * @param bundleManager
     * @return
     */
    public Object navigation(Context context, BundleManager bundleManager) {
        String groupClassName = context.getPackageName() + "." + FILE_SUFFIX_NAME + group;

        try {
            ARouterGroup aRouterGroup = groupLruCache.get(group);
            if (aRouterGroup == null) {
                // com.zj.new_modular_customarouter
                Class<?> aClass = Class.forName(groupClassName);
                aRouterGroup = (ARouterGroup) aClass.newInstance();
                groupLruCache.put(group, aRouterGroup);
            }

            if (aRouterGroup.getGroupMap().isEmpty()) {
                throw new NullPointerException("路由表Group是空的");
            }

            ARouterPath aRouterPath = pathLruCache.get(path);
            if (aRouterPath == null) {
                Class<? extends ARouterPath> aClass = aRouterGroup.getGroupMap().get(group);
                aRouterPath = aClass.newInstance();
                pathLruCache.put(path, aRouterPath);
            }

            if (!aRouterPath.getPathMap().isEmpty()) {
                RouterBean routerBean = aRouterPath.getPathMap().get(path);
                if (routerBean != null) {
                    switch (routerBean.getTypeEnum()) {
                        case ACTIVITY:
                            Intent intent = new Intent(context, routerBean.getMyClass());
                            intent.putExtras(bundleManager.getBundle());
                            context.startActivity(intent, bundleManager.getBundle());
                            break;
                        case CALL:
                            Class<?> clazz = routerBean.getMyClass();
                            Call call = (Call) clazz.newInstance();
                            bundleManager.setCall(call);
                            return bundleManager.getCall();
                        default:
                            break;
                    }
                }
            } else {
                throw new NullPointerException("路由表Path是空的");
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
