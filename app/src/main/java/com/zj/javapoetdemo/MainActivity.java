package com.zj.javapoetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zj.arouter_api.RouterManager;
import com.zj.javapoetdemo_annotations.ARouter;
import com.zj.javapoetdemo_annotations.Parameter;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Parameter
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toOther(View view) {
        RouterManager.getInstance()
                .build("/other/OtherActivity")
                .withInt("age", 19)
                .withString("name", "xxx")
                .navigation(this);
    }
}