package com.fruits.pro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fruits.pro.Constant;
import com.fruits.pro.R;
import com.fruits.pro.base.BaseActivity;

import net.grandcentrix.tray.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by cwj on 16/7/17.
 */
public class LaunchActivity extends BaseActivity {
    @Bind(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @Bind(R.id.tv_guide_skip)
    TextView mSkipTv;
    @Bind(R.id.btn_guide_enter)
    Button mEnterBtn;
    private AppPreferences sharedPreference;
    @Override
    protected int getLayoutResId() {
        return R.layout.act_launch;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.translucentStatusBar(this);
        sharedPreference= new AppPreferences(this);
        boolean isFirst = sharedPreference.getBoolean(Constant.FIRST_LAUNCH,true);
        if(isFirst){
            processLogic();
        }else{
            startActivity(new Intent(this, MainActivity.class));
            closeCurrentActivity();
            overridePendingTransition(0,0);
        }

    }

    @OnClick({R.id.tv_guide_skip,R.id.btn_guide_enter})
    @Override
    public void onClick(View view) {
        sharedPreference.put(Constant.FIRST_LAUNCH,false);
        home();
    }
    public void home(){
        startActivity(new Intent(this, MainActivity.class));
        closeCurrentActivity();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    private void processLogic() {
        mContentBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);

        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.ic_guide_1));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.ic_guide_2));
        views.add(BGABannerUtil.getItemImageView(this, R.mipmap.ic_guide_3));
        mContentBanner.setData(views);

        mContentBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == mContentBanner.getItemCount() - 2) {
                    ViewCompat.setAlpha(mEnterBtn, positionOffset);
                    ViewCompat.setAlpha(mSkipTv, 1.0f - positionOffset);

                    if (positionOffset > 0.5f) {
                        mEnterBtn.setVisibility(View.VISIBLE);
                        mSkipTv.setVisibility(View.GONE);
                    } else {
                        mEnterBtn.setVisibility(View.GONE);
                        mSkipTv.setVisibility(View.VISIBLE);
                    }
                } else if (position == mContentBanner.getItemCount() - 1) {
                    mSkipTv.setVisibility(View.GONE);
                    mEnterBtn.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mEnterBtn, 1.0f);
                } else {
                    mSkipTv.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mSkipTv, 1.0f);
                    mEnterBtn.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mContentBanner.setBackgroundResource(android.R.color.white);
    }
}
