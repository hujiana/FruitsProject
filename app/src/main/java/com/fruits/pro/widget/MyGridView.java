package com.fruits.pro.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 在ScrollView、ListView等可滚动空间中嵌套的GridView
 * 全部展开，不可滚动
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//    	return MotionEvent.ACTION_MOVE == ev.getAction() ? true
//                : false;
//    }
}
