package com.fruits.pro.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.base.ToolBarActivity;
import com.fruits.pro.ui.bbs.BBSFragment;
import com.fruits.pro.ui.home.HomeFragment;
import com.fruits.pro.ui.mine.MineFragment;
import com.fruits.pro.ui.sort.SortFragment;
import com.fruits.pro.widget.MyRadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.fruits.pro.widget.MyRadioGroup.OnCheckedChangeListener;

public class MainActivity extends ToolBarActivity implements OnCheckedChangeListener {
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.rb_tab_1)
    RadioButton rbTab1;
    @Bind(R.id.rb_tab_2)
    RadioButton rbTab2;
    @Bind(R.id.rb_tab_3)
    RadioButton rbTab3;
    @Bind(R.id.rb_tab_4)
    RadioButton rbTab4;
    @Bind(R.id.rg_navigation)
    MyRadioGroup rgNavigation;
    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private BBSFragment bbsFragment;
    private MineFragment mineFragment;
    private List<BaseFragment> mFragments = new ArrayList<BaseFragment>();
    private long mExitTime;

    @Override
    protected int getLayoutResId() {
        return R.layout.act_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rgNavigation.setOnCheckedChangeListener(this);
        ((RadioButton)findViewById(R.id.rb_tab_1)).setChecked(true);
    }

    @Override
    public void onClick(View view) {


    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_tab_1:
                initTitle("首页");
                initFragment(0);
                break;
            case R.id.rb_tab_2:
                initTitle("分类");
                initFragment(1);
                break;
            case R.id.rb_tab_3:
                initTitle("个人中心");
                initFragment(2);
                break;
            case R.id.rb_tab_4:
                initTitle("社区");
                initFragment(3);
                break;
            default:
                break;

        }
    }
    private void initFragment(int position){
        try {
            if(position==0){
                if(homeFragment ==null){
                    homeFragment =new HomeFragment();
                }else{
                    homeFragment.onRefresh();
                }
                changeFragment(homeFragment);
            }else if(position==1){
                if(sortFragment ==null){
                    sortFragment =new SortFragment();
                }else{
                    sortFragment.onRefresh();
                }
                changeFragment(sortFragment);
            }else if(position==2){
                if(mineFragment==null){
                    mineFragment=new MineFragment();
                }else{
                    mineFragment.onRefresh();
                }
                changeFragment(mineFragment);
            }else if(position==3){
                if(bbsFragment==null){
                    bbsFragment=new BBSFragment();
                }else{
                    bbsFragment.onRefresh();
                }
                changeFragment(bbsFragment);
            }
        } catch (Exception e) {

        }
    }
    private void changeFragment(BaseFragment baseFragment) {
        FragmentTransaction mBeginTransaction = this.getSupportFragmentManager().beginTransaction();
        if(!mFragments.contains(baseFragment)) {
            mFragments.add(baseFragment);
            mBeginTransaction.add(R.id.fragment_container, baseFragment);
        }
        for(BaseFragment mBaseFragment : mFragments) {
            if(mBaseFragment == baseFragment) {
                mBeginTransaction.show(mBaseFragment);
            }else {
                mBeginTransaction.hide(mBaseFragment);
            }
        }
        mBeginTransaction.commitAllowingStateLoss();
    }

}
