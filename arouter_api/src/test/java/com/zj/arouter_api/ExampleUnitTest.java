package com.zj.arouter_api;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        String s = "^/*/*";
        String pa = "/order/Order_MainActivity";
        String ss = "^/[a-z]+";
        boolean matches = Pattern.matches(s, pa);
        System.out.println(matches);
    }
}