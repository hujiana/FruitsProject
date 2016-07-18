package com.fruits.pro.presenters;

import android.content.Context;

import com.fruits.pro.base.BasePresenter;
import com.fruits.pro.base.IBaseView;

/**
 * Created by cwj on 16/7/18.
 */
public class BBSPresenter extends BasePresenter<BBSPresenter.IBBSView> {

    public BBSPresenter(Context context, IBBSView iView) {
        super(context, iView);
    }



    @Override
    public void release() {

    }

    public interface IBBSView extends IBaseView{

    }
}
