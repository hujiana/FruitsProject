package com.fruits.pro.presenters;

import android.content.Context;

import com.fruits.pro.base.BasePresenter;
import com.fruits.pro.base.IBaseView;

/**
 * Created by cwj on 16/7/18.
 */
public class MinePresenter extends BasePresenter<MinePresenter.IMineView> {
    public MinePresenter(Context context, IMineView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public interface IMineView extends IBaseView{

    }
}
