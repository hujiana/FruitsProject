package com.fruits.pro.presenters;

import android.content.Context;

import com.fruits.pro.base.BasePresenter;
import com.fruits.pro.base.IBaseView;

/**
 * Created by cwj on 16/7/18.
 */
public class HomePresenter extends BasePresenter<HomePresenter.IHomeView> {
    public HomePresenter(Context context, IHomeView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public interface IHomeView extends IBaseView{

    }
}
