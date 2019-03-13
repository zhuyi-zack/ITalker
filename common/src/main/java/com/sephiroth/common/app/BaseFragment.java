package com.sephiroth.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Sephiroth on 2019/3/12.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getContentLayoutId(), container, false);
            initWidget(mRootView);
            initData();
        } else {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
        return mRootView;
    }

    /**
     * 初始化界面传递数据
     *
     * @param bundle 组件间传递数据bundle
     */
    protected void initArgs(Bundle bundle) {
    }

    /**
     * 获取当前Fragment的布局资源id
     *
     * @return 布局资源id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化布局资源
     *
     * @param rootView
     */
    protected void initWidget(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    /**
     * 初始化当前Fragment数据
     */
    protected void initData() {
    }

    /**
     * 按返回键时触发调用
     *
     * @return 返回false表示当前fragment不处理按手机返回键后的逻辑
     * 返回true表示当前fragment处理按手机返回键后的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }
}
