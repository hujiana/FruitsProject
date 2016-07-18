package com.fruits.pro.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.fruits.pro.R;
import com.fruits.pro.base.BaseFragment;
import com.fruits.pro.base.ToolBarActivity;
import com.fruits.pro.ui.home.HomeFragment;

/**
 * Created by cwj on 16/7/18.
 */
public class AgentActivity extends ToolBarActivity {
    /**测试*/
    public static final int FRAG_TEST=0x0001;
    public static final String EXTRA_FRAGMENT = "extra_fragment";

    BaseFragment fragment=null;
    @Override
    protected int getLayoutResId() {
        return R.layout.act_agent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int fragmentid = getIntent().getIntExtra(EXTRA_FRAGMENT, -1);
        switch (fragmentid){
            case FRAG_TEST:
                fragment=new HomeFragment();
                break;
            default:
                finish();
                break;
        }
        // 插入Fragment
        if (fragment != null) {
            fragment.setArguments(bundle);
            setMainFragment(fragment);
        }
    }
    /**
     * 返回打开Fragment的Intent
     *
     * @param context
     * @param fragment
     * @return
     */
    public static Intent intentForFragment(Context context, int fragment) {
        Intent intent = new Intent(context, AgentActivity.class);
        intent.putExtra(EXTRA_FRAGMENT, fragment);
        return intent;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (fragment.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 设置主Fragment
     *
     * @param fragment
     */
    public void setMainFragment(BaseFragment fragment) {
        setMainFragment(fragment, 0, 0);
    }

    /**
     * 设置主Fragment和切换动画
     *
     * @param fragment
     * @param enter
     *            进场动画id
     * @param exit
     *            出场动画id
     */
    public void setMainFragment(BaseFragment fragment, int enter, int exit) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (enter != 0 && exit != 0) {
            transaction.setCustomAnimations(enter, exit);
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    @Override
    protected void initPresenter() {

    }
}
