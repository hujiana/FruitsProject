package com.fruits.pro.ui.sort;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.presenters.SortPresenter;
import com.fruits.pro.presenters.SortPresenter.ISortView;

/**
 * Created by cwj on 16/7/18.
 */
public class SortFragment extends BaseFragment<SortPresenter> implements ISortView {
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_sort;
    }

    @Override
    protected void initPresenter() {
        presenter=new SortPresenter(mContext,this);
    }

    @Override
    public void initView() {

    }



    public void onRefresh() {

    }
}
