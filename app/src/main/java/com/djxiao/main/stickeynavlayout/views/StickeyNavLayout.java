package com.djxiao.main.stickeynavlayout.views;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;

import com.djxiao.main.stickeynavlayout.R;

/**
 * @author djxiao
 * @create 2016/8/29  14:45
 * @Desc:
 */
public class StickeyNavLayout extends LinearLayout implements NestedScrollingParent {

    private View topView;
    private View navView;
    private ViewPager viewPager;
    private OverScroller mScroller;
    private int topViewHeight;
//    private int touchSlop;
//    private int minVelocity;
//    private int maxVelocity;



    public StickeyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context);
//        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        minVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
//        maxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = getMeasuredHeight() - navView.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(),topView.getMeasuredHeight()+navView.getMeasuredHeight()+viewPager.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        topViewHeight = topView.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        topView = findViewById(R.id.id_top_view);
        navView = findViewById(R.id.id_nav_view);
        View view = findViewById(R.id.id_view_pager);
        if(!(view instanceof ViewPager)){
            throw  new RuntimeException("类型转换错误");
        }
        viewPager = (ViewPager) view;
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i("Tag","onStartNestedScroll");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.i("Tag","onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(View child) {
        Log.i("Tag","onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("Tag","onNestedScroll");
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.i("Tag","onNestedPreScroll");
        boolean hideTop = dy > 0 && getScrollY() < topViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target,-1);
        if(hideTop || showTop){
            scrollBy(0,dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.i("Tag","onNestedPreFling");
        if(getScrollY() >= topViewHeight){
            return false;
        }else{
            fling((int) velocityY);
            return true;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i("Tag","onNestedFling");
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        Log.i("Tag","getNestedScrollAxes");
        return 0;
    }

    private void fling(int velocityY){
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,topViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if(y < 0){
            y = 0;
        }

        if(y > topViewHeight){
            y = topViewHeight;
        }

        if(y != getScrollY()){
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }
}
