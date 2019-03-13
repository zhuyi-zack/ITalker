package com.sephiroth.common.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * @author Sephiroth on 2019/3/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        if (initArgs(getIntent().getExtras())) {
            setContentView(getContentLayoutId());
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindow() {
    }

    /**
     * 初始化界面传递数据
     *
     * @param bundle android组件间传递数据bundle
     * @return 传递数据正确返回true，传递数据错误返回false，不复写时默认返回true
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 获取当前activity的布局id
     *
     * @return 布局资源id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化布局资源
     */
    protected void initWidget() {
    }

    /**
     * 初始化当前activity数据
     */
    protected void initData() {
    }

    /**
     * 当点击toolbar上的返回按钮时，销毁当前activity
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 当按手机上的实体返回时的逻辑
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).onBackPressed();
                return;
            }
        }

        finish();
    }
}
