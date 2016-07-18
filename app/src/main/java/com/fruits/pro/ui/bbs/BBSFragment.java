package com.fruits.pro.ui.bbs;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.presenters.BBSPresenter;
import com.fruits.pro.presenters.BBSPresenter.IBBSView;

/**
 * Created by cwj on 16/7/18.
 */
public class BBSFragment extends BaseFragment<BBSPresenter> implements IBBSView {
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_bbs;
    }

    @Override
    protected void initPresenter() {
        presenter=new BBSPresenter(mContext,this);
    }

    @Override
    public void initView() {

    }

    public void onRefresh() {

    }
}
