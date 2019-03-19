package com.sephiroth.italker;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.sephiroth.common.app.BaseActivity;
import com.sephiroth.common.widget.PortraitView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.portrait)
    PortraitView mPortraitView;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.lay_containar)
    FrameLayout mContainar;

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mNavigationView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mAppBarLayout) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        view.setBackground(resource.getCurrent());
                    }
                });
    }

    @OnClick(R.id.im_search)
    protected void onSearch() {

    }

    @OnClick(R.id.float_action_button)
    protected void onAction() {

    }

    @OnClick(R.id.portrait)
    protected void onPortraitClick() {

    }
}
