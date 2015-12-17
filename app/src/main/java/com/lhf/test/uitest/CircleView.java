package com.lhf.test.uitest;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * com.lhf.test.uitest
 * Created by zeratel3000
 * on 2015 11 15/11/18 22 52
 * description
 */
public class CircleView extends View{

    Rect rectF;
    Paint mCirclePaint = new Paint();
    Paint mAccBallPaint = new Paint();
    private float mAccBallRadius = 50;
    private float sweepAngle = 0;
    private long mDuration = 5000;

    public CircleView(Context context) {
        super(context);
        Log.i("LHF1", "1");
        Log.i("LHF1", "2");
        Log.i("LHF1", "3");
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("LHF1", "2");
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("LHF1", "3");
        init();
    }

    private void init(){
        mCirclePaint.setColor(0xffff0000);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5f);
        mAccBallPaint.setColor(0xff00ff00);

        smallCircleAnimator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (rectF == null) {
            //这个是距离，不带正负
            rectF = new Rect(-getLeft(),-getTop(),getRight(),getBottom());
        }
//        rectF = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        drawBigCircle(canvas);
        drawSmallCircle(canvas);

    }

    private void drawBigCircle(Canvas canvas){
        if (rectF != null) {

            canvas.drawCircle(rectF.centerX(), rectF.centerY(), getBigCircleRadius(), mCirclePaint);
            Log.e("LHF1", "rectF.centerX():" + rectF.centerX() + ",rectF.centerY()" + rectF.centerY());

            //79 1001
            //540 792

        }
    }

    private float getBigCircleRadius() {
        return 300;
    }

    private void drawSmallCircle(Canvas canvas){
        if (rectF != null) {

//            double sweepAngle = Math.PI/180 * 45;
            double sweepAngle2 = Math.PI/180 * sweepAngle;

            //caculate the circle's center(x, y)
            float y = (float)Math.sin(sweepAngle2)*(getBigCircleRadius());
            float x = (float)Math.cos(sweepAngle2)*(getBigCircleRadius());
            int restoreCount = canvas.save();
            //change aix center position
            canvas.translate(rectF.centerX(), rectF.centerY());
            canvas.drawCircle(x, y, mAccBallRadius, mAccBallPaint);
            canvas.restoreToCount(restoreCount);

            Log.e("LHF1","rectF.left:"+rectF.left+",rectF.top:"+rectF.top+",rectF.right:"+rectF.right+",rectF.bottom:"+rectF.bottom);
        }
    }

    private void smallCircleAnimator(){

        AccTypeEvaluator accCore = new AccTypeEvaluator();
        ValueAnimator animator = ValueAnimator.ofObject(accCore, 0.0f, 360.0f);
        animator.setDuration(mDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                sweepAngle = value;
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }


    public class AccTypeEvaluator implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            Log.d("", " current fraction == " + fraction);
            return fraction * (endValue - startValue);
        }
    }
}
