package com.zhihaoliang.util.ui.zuisimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhihaoliang.util.ui.UIUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIUtil.showToast(this,"qww");
    }
}
