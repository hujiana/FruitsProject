package com.fruits.pro.presenters;

import android.content.Context;

import com.fruits.pro.base.BasePresenter;
import com.fruits.pro.base.IBaseView;

/**
 * Created by cwj on 16/7/17.
 */
public class MainPresenter extends BasePresenter<MainPresenter.IMainView>{

    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public interface IMainView extends IBaseView{

    }
}
