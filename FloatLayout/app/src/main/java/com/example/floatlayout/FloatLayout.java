package com.example.floatlayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FloatLayout extends ViewGroup {
    private static int mHorizontalSpacing = dp2px(16);
    private static int mVerticalSpacing = dp2px(8);
    private List<List<View>> allLines;
    private List<Integer> lineHeights = new ArrayList<>();
    public FloatLayout(Context context) {
        super(context);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected  void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        init();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        List<View> lineViews = new ArrayList<>();
        int lineWidthUsed = 0;
        int lineHeight = 0;

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        int parentNeededWidth = 0;
        int parentNeededHeight = 0;
        for(int i = 0;i < childCount;i++){
            View childView = getChildAt(i);
            //子控件告诉父控件，自己要如何布局
            LayoutParams childLP = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,paddingLeft + paddingRight,childLP.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,paddingTop + paddingBottom,childLP.height);
            childView.measure(childWidthMeasureSpec,childHeightMeasureSpec);
            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasuredHeight = childView.getMeasuredHeight();

            if (childMeasuredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth){
                allLines.add(lineViews);
                lineHeights.add(lineHeight);

                parentNeededHeight = parentNeededHeight +  lineHeight + mVerticalSpacing;
                parentNeededWidth = Math.max(parentNeededWidth,lineWidthUsed + mHorizontalSpacing);

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }
            //处理最后一个
            if(i == childCount - 1){
                allLines.add(lineViews);
                lineHeights.add(lineHeight);
            }
            lineViews.add(childView);
            lineWidthUsed = lineWidthUsed + childMeasuredWidth + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight,childMeasuredHeight);

            //保存
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int realWidth = (widthMode == MeasureSpec.EXACTLY)?selfWidth:parentNeededWidth;
            int realHeight = (heightMode == MeasureSpec.EXACTLY)?selfHeight:parentNeededHeight;
            setMeasuredDimension(realWidth,realHeight);
        }
    }

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int lineCount = allLines.size();
        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        for(int i = 0;i < lineCount;i++){
            List<View> lineViews = allLines.get(i);
            int height = lineHeights.get(i);
            for(int j = 0; j < lineViews.size();j++){
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;
                //getWidth在layout结束后
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
                curL = right + mHorizontalSpacing;
            }
            curL = 0;
            curT = curT + height + mVerticalSpacing;
        }
    }

    private void init(){
        allLines = new ArrayList<>();
        lineHeights = new ArrayList<>();
    }

    private static int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().getDisplayMetrics());
    }
}
