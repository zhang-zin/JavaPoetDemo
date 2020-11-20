package com.zj.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zj.arouter_api.ParameterManager;
import com.zj.javapoetdemo_annotations.ARouter;
import com.zj.javapoetdemo_annotations.Parameter;

@ARouter(path = "/My/MYActivity")
public class MYActivity extends AppCompatActivity {

    @Parameter
    int age;

    @Parameter
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_y);

        ParameterManager.getInstance().loadParameter(this);
        Log.e("MYActivity", "age = " + age + ", name = " + name);
    }

    public void toMy(View view) {
    }
}