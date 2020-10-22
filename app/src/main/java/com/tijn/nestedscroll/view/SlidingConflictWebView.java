package com.tijn.nestedscroll.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.example.nestedscrollwebview.NestedScrollWebView;

/**
 * 处理跟viewpager 滑动冲突问题
 */
public class SlidingConflictWebView extends NestedScrollWebView {
    private boolean actionSetting;//点击当前位置viewpager不能切换

    public SlidingConflictWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SlidingConflictWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlidingConflictWebView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
    }

    /**
     * -这处理viewpager和webview内容横向滑动冲突事件----------------------------------------------------------------------------
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //事件由webview处理
                ViewParent parent = findViewParentIfNeeds();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //事件由webview处理
                parent = findViewParentIfNeeds();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                actionSetting = false;
                break;

        }
        return super.onTouchEvent(event);
    }

    //当webview滚动到边界时执行
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (clampedX && !actionSetting) {
            ViewParent parent = findViewParentIfNeeds();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }

    }

    private ViewParent findViewParentIfNeeds() {
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent instanceof ViewPager) {
                return parent;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
    }

    public void setViewpagerDisallowInterceptTouchEvent() {
        actionSetting = true;
        //事件由webview处理
        ViewParent parent =getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

}