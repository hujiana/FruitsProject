package com.fruits.pro.base;

import android.app.Application;

import com.fruits.pro.R;
import com.fruits.pro.utils.jutils.JActivityManager;
import com.fruits.pro.utils.jutils.JFileManager;
import com.fruits.pro.utils.jutils.JUtils;

import net.grandcentrix.tray.AppPreferences;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by cwj on 16/7/16.
 */
public class BaseApplication extends Application {
    private AppPreferences preferences;
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Activity的管理类,保持所有存在activity引用.以下例子可以在任何地方使用
         * JActivityManager.getInstance().currentActivity()获取当前最顶层activity
         * JActivityManager.getInstance().closeActivity(Activity activity)关闭activity
         * JActivityManager.getInstance().closeAllActivity()关闭所有activity
         */
        registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());

        //初始化传入App文件目录列表。会在app的目录下会生成(Dir枚举)数个文件夹
        /**
         * 根据枚举类型获取目录。Folder对象提供本目录下多种文件存取操作
         * JFileManager.Folder folder = JFileManager.getInstance().getFolder(JFileManager.Dir.Image);
         */
        JFileManager.getInstance().init(this,JFileManager.Dir.values());
        /**
         * JUtil工具类初始化
         */
        JUtils.initialize(this);
        //初始化字体
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FZLTXHJW.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
