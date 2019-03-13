package com.sephiroth.italker;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.sephiroth.common.app.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView mTest;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        mTest = findViewById(R.id.tv_test);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        mTest.setText("hello world");
    }
}
