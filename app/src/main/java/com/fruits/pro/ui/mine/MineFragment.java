package com.fruits.pro.ui.mine;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.presenters.MinePresenter;

import static com.fruits.pro.presenters.MinePresenter.*;

/**
 * Created by cwj on 16/7/18.
 */
public class MineFragment extends BaseFragment<MinePresenter> implements IMineView {
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initPresenter() {
        presenter=new MinePresenter(mContext,this);
    }

    @Override
    public void initView() {

    }


    public void onRefresh() {

    }
}
