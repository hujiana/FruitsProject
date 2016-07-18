package com.fruits.pro.presenters;

import android.content.Context;

import com.fruits.pro.base.BasePresenter;
import com.fruits.pro.base.IBaseView;

/**
 * Created by cwj on 16/7/18.
 */
public class SortPresenter extends BasePresenter<SortPresenter.ISortView> {
    public SortPresenter(Context context, ISortView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }

    public interface ISortView extends IBaseView{

    }
}
