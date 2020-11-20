package com.zj.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zj.arouter_api.ParameterManager;
import com.zj.arouter_api.RouterManager;
import com.zj.javapoetdemo_annotations.ARouter;
import com.zj.javapoetdemo_annotations.Parameter;

/**
 * @author zhangjin
 */
@ARouter(path = "/other/OtherActivity")
public class OtherActivity extends AppCompatActivity {

    @Parameter
    int age;

    @Parameter
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ParameterManager.getInstance().loadParameter(this);
        Log.e("OtherActivity", "age = " + age + ", name = " + name);
    }

    public void toMy(View view) {
        RouterManager.getInstance()
                .build("/My/MYActivity")
                .withInt("age", 19)
                .withString("name", "xxx")
                .navigation(this);
    }
}