package com.zj.javapoetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zj.javapoetdemo_annotations.ARouter;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}