package com.fruits.pro.ui;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fruits.pro.R;
import com.fruits.pro.base.BaseActivity;
import com.fruits.pro.presenters.MainPresenter;
import com.fruits.pro.presenters.MainPresenter.IMainView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView {
    @Bind(R.id.iv)
    ImageView iv;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this, this);
    }

    @OnClick(R.id.tv)
    @Override
    public void onClick(View view) {
//        closeCurrentActivity();
        new Thread(){
            @Override
            public void run() {
                Glide.get(MainActivity.this).clearDiskCache();
            }
        }.start();

    }

    @Override
    public void initView() {
        Glide.with(this).load("http://image.tianjimedia.com/uploadImages/2012/231/59/W19D0E6GL776.jpg").into(iv);
    }

    @Override
    public void initData() {

    }

}
