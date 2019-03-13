package com.sephiroth.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Sephiroth on 2019/3/12.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initArgs(getArguments());
        mRootView = inflater.inflate(getContentLayoutId(), container, false);
        initWidget(mRootView);
        initData();
        return mRootView;
    }

    protected void initArgs(Bundle bundle){}

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
    }

    /**
     * 初始化当前Fragment数据
     */
    protected void initData() {
    }

    public void onBackPressed(){

    }
}
