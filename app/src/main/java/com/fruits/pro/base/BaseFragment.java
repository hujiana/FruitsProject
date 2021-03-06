package com.fruits.pro.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by cwj on 16/7/17.
 */
public  abstract  class BaseFragment<T extends BasePresenter> extends Fragment implements OnClickListener{
    public BaseActivity mActivity;
    public LayoutInflater mInflater;
    public Context mContext;
    protected T presenter;

    /**
     * Notice:Never use this constructor<br />
     * 只是防止Fragment重载崩溃
     */
    public BaseFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;
    }
    protected void initToolBarTitle(CharSequence title){
        ToolBarActivity toolBarActivity=(ToolBarActivity)getActivity();
        toolBarActivity.initTitle(title);
    }
    protected void initToolBarTitle(int resId){
        ToolBarActivity toolBarActivity=(ToolBarActivity)getActivity();
        toolBarActivity.initTitle(resId);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mActivity =(BaseActivity)activity;
        } catch (Exception e) {
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view;
        if(getLayoutResId()>0){
            view= inflater.inflate(getLayoutResId(), container, false);
        }else{
            view=super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, view);
        initPresenter();
        //想让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法，否则Toolbar没有菜单
        setHasOptionsMenu(true);
        return view;
    }
    /**
     * 创建View
     */
    protected abstract int getLayoutResId();
    protected abstract void initPresenter();
    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected  void onUserVisible(){}
    protected <VT extends View> VT findViewById(int id) {
        if(getView()!=null)
            return  (VT)getView().findViewById(id);
        else
            return null;
    }
    protected <VT extends View> VT findViewById(int id,OnClickListener listener){
        VT view=findViewById(id);
        if(view!=null){
            view.setOnClickListener(listener);
        }
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.release();
        }
    }

    @Override
    public void onClick(View v) {

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
