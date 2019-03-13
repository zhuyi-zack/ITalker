package com.sephiroth.italker;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.sephiroth.common.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_test)
    TextView mTextView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initWidget() {
        super.initWidget();
        mTextView.setText("butterKnife");
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        super.initData();
    }
}
