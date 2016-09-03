package com.djxiao.main.stickeynavlayout.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author djxiao
 * @create 2016/8/31  15:03
 * @Desc:
 */
public class IndicatorView extends LinearLayout {

    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    private static final int COLOR_INDICATOR_COLOR = Color.GREEN;

    private String[] titles;
    private Paint mPaint;
    private int indicateColor = COLOR_INDICATOR_COLOR;
    private int mTabCount;
    private int mTabWidth;
    private float mTransationX;

    public IndicatorView(Context context) {
        this(context,null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(indicateColor);
        mPaint.setStrokeWidth(9);
    }

    public void setTitles(String[] titles){
        this.titles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = getWidth() / mTabCount;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTransationX,getHeight()-2);
        canvas.drawLine(0,0,mTabWidth,0,mPaint);
        canvas.restore();
    }

    public void scoll(int position,float offset){
        mTransationX = getWidth()/mTabCount*(position+offset);
        invalidate();
    }

    private void generateTitleView(){
        if(getChildCount() > 0){
            removeAllViews();
        }

        int count = titles.length;
        setWeightSum(count);
        for(int i = 0;i<count;i++){
            TextView tv = new TextView(getContext());
            LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tv.setText(titles[i]);
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            addView(tv);
        }
    }

}
