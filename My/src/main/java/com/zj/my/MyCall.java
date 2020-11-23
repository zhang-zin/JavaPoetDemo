package com.zj.my;

import com.zj.call.ICall;
import com.zj.javapoetdemo_annotations.ARouter;

@ARouter(path = "/My/MyCall")
public class MyCall implements ICall {

    @Override
    public int getId() {
        return 1;
    }
}
