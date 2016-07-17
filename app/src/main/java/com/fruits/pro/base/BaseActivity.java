package com.fruits.pro.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.fruits.pro.BuildConfig;
import com.fruits.pro.R;
import com.fruits.pro.utils.jutils.JActivityManager;

import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by xybcoder on 2016/3/1.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();
    protected T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //debug模式下 启用严格模式
        if (BuildConfig.DEBUG&& Build.VERSION.SDK_INT >=Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this,R.color.transparent);
        this.initPresenter();
    }
    protected abstract int getLayoutResId();

    public <VT extends View> VT findViewById(int id, OnClickListener listener) {
        VT view= (VT) findViewById(id);
        view.setOnClickListener(listener);
        return view;
    }
    protected abstract void initPresenter();

    public void onClick(View view) {};
    public void startActivity(Class<?> mClass, boolean isFinish, Bundle bundle) {
        if(null != bundle) {
            Intent mIntent = new Intent(this, mClass);
            mIntent.putExtras(bundle);
            this.startActivity(mIntent);
        }else {
            this.startActivity(new Intent(this, mClass));
        }
        if(isFinish) {
            finish();
        }
    }

    public void startActivityForResult(Class<?> mClass, boolean isFinish, Bundle bundle, int requestCode) {
        if(null != bundle) {
            Intent mIntent = new Intent(this, mClass);
            mIntent.putExtras(bundle);
            this.startActivityForResult(mIntent, requestCode);
        }else {
            this.startActivityForResult(new Intent(this, mClass), requestCode);
        }
        if(isFinish) {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        //资源释放
        if(presenter!=null){
            presenter.release();
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public void closeCurrentActivity(){
        try {
            JActivityManager.closeActivity(this);
        } catch (Exception e) {
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    @SuppressLint("NewApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    };

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                break;
            default:
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
