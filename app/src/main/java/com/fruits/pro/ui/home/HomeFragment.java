package com.fruits.pro.ui.home;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.presenters.HomePresenter;
import com.fruits.pro.presenters.HomePresenter.IHomeView;
import com.fruits.pro.ui.AgentActivity;

import butterknife.OnClick;

/**
 * Created by cwj on 16/7/18.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView {
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_home;
    }

    @Override
    protected void initPresenter() {
        presenter=new HomePresenter(mContext,this);
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.button,R.id.button2})
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.button:
                mActivity.getSupportActionBar().hide();
                break;
            case R.id.button2:
                startActivity(AgentActivity.intentForFragment(mActivity,AgentActivity.FRAG_TEST));
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_web, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onRefresh() {

    }
}
