package com.fruits.pro.base;

import android.content.Context;

import rx.Subscription;

/**
 * 基础presenter
 * Created by cwj
 */
public abstract class BasePresenter<T extends IBaseView>  {

    protected Subscription subscription;
    protected Context context;
    protected T iView;

    public BasePresenter(Context context, T iView) {
        this.context = context;
        this.iView = iView;
        init();
    }

    private void init(){
        iView.initView();
        iView.initData();
    }

    public abstract void release();
}
