package com.zj.javapoetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zj.arouter_api.ParameterManager;
import com.zj.arouter_api.RouterManager;
import com.zj.call.ICall;
import com.zj.javapoetdemo_annotations.ARouter;
import com.zj.javapoetdemo_annotations.Parameter;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Parameter(name = "/My/MyCall")
    ICall iCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParameterManager.getInstance().loadParameter(this);
        int id = iCall.getId();
        Log.e("MainActivity", id + "");
    }

    public void toOther(View view) {
        RouterManager.getInstance()
                .build("/other/OtherActivity")
                .withInt("age", 19)
                .withString("name", "xxx")
                .navigation(this);
    }
}