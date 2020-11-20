package com.zj.arouter_api;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 传递参数
 *
 * @author zhangjin
 */
public class BundleManager {

    private Bundle bundle = new Bundle();

    public Bundle getBundle() {
        return bundle;
    }

    public BundleManager withString(@NonNull String key, @Nullable String value) {
        bundle.putString(key, value);
        return this;
    }

    public BundleManager withInt(@NonNull String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public BundleManager withBoolean(@NonNull String key, boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }

    public BundleManager withBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public void navigation(Context context) {
        RouterManager.getInstance().navigation(context, this);
    }
}
