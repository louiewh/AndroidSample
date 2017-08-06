package com.dawang.androidexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

import com.dawang.androidexample.R;

import java.util.ArrayList;

/**
 * Created by DaWang on 2017/7/30.
 */

public class GradientFragment extends RecyclerViewFragment {
    @Override
    public ArrayList<View> getViewList() {
        ArrayList<View> arrayList = new ArrayList<>();
        arrayList.add(new LinearGradientView(getContext()));
        arrayList.add(new RadialGradientView(getContext()));
        arrayList.add(new SweepGradientView(getContext()));

        arrayList.add(new LinearClampGradientView(getContext()));
        arrayList.add(new LinearRepeatGradientView(getContext()));
        arrayList.add(new LinearMirrorGradientView(getContext()));

        arrayList.add(new RadialClampGradientView(getContext()));
        arrayList.add(new RadialRepeatGradientView(getContext()));
        arrayList.add(new RadialMirrorGradientView(getContext()));

        arrayList.add(new DrawableLinearGradientView(getContext()));
        arrayList.add(new DrawableRadialGradientView(getContext()));
        arrayList.add(new DrawableSweepGradientView(getContext()));

        return arrayList;
    }

    class LinearGradientView extends PaintCanvasView{

        public LinearGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            LinearGradient linearGradient = new LinearGradient(
                    0, 0,
                    mRectF.right, mRectF.top,
                    Color.RED, Color.YELLOW,
                    Shader.TileMode.MIRROR
            );

            mPaint.setShader(linearGradient);
            canvas.drawRect(mRectF, mPaint);
        }
    }


    class RadialGradientView extends PaintCanvasView{

        public RadialGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            RadialGradient radialGradient = new RadialGradient(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    new int[]{Color.RED,  Color.YELLOW, Color.BLUE}, null,
                    Shader.TileMode.MIRROR
            );

            mPaint.setShader(radialGradient);
            canvas.drawCircle(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    mPaint
            );
        }
    }

    class SweepGradientView extends PaintCanvasView{

        public SweepGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            SweepGradient sweepGradient = new SweepGradient(
                    mRectF.centerX(), mRectF.centerY(),
                    new int[]{Color.RED, Color.YELLOW, Color.BLACK, Color.MAGENTA},
                    null
            );

            mPaint.setShader(sweepGradient);
            canvas.drawCircle(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    mPaint
            );
        }
    }

    class LinearClampGradientView extends PaintCanvasView{

        public LinearClampGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            LinearGradient linearGradient = new LinearGradient(
                    0, mRectF.centerY(),
                    mRectF.centerX(), mRectF.centerY(),
                    Color.RED, Color.YELLOW,
                    Shader.TileMode.CLAMP
            );

            mPaint.setShader(linearGradient);
            canvas.drawRect(mRectF, mPaint);
        }
    }

    class LinearRepeatGradientView extends PaintCanvasView{

        public LinearRepeatGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            LinearGradient linearGradient = new LinearGradient(
                    0, mRectF.centerY(),
                    mRectF.centerX(), mRectF.centerY(),
                    Color.RED, Color.YELLOW,
                    Shader.TileMode.REPEAT
            );

            mPaint.setShader(linearGradient);
            canvas.drawRect(mRectF, mPaint);
        }
    }

    class LinearMirrorGradientView extends PaintCanvasView{

        public LinearMirrorGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            LinearGradient linearGradient = new LinearGradient(
                    0, mRectF.centerY(),
                    mRectF.centerX(), mRectF.centerY(),
                    Color.RED, Color.YELLOW,
                    Shader.TileMode.MIRROR
            );

            mPaint.setShader(linearGradient);
            canvas.drawRect(mRectF, mPaint);
        }
    }

    class RadialClampGradientView extends PaintCanvasView{

        public RadialClampGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            RadialGradient radialGradient = new RadialGradient(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY())/2,
                    new int[]{Color.RED,  Color.YELLOW, Color.BLUE}, null,
                    Shader.TileMode.CLAMP
            );

            mPaint.setShader(radialGradient);
            canvas.drawCircle(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    mPaint
            );
        }
    }

    class RadialRepeatGradientView extends PaintCanvasView{

        public RadialRepeatGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            RadialGradient radialGradient = new RadialGradient(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY())/2,
                    new int[]{Color.RED,  Color.YELLOW, Color.BLUE}, null,
                    Shader.TileMode.REPEAT
            );

            mPaint.setShader(radialGradient);
            canvas.drawCircle(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    mPaint
            );
        }
    }

    class RadialMirrorGradientView extends PaintCanvasView{

        public RadialMirrorGradientView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            RadialGradient radialGradient = new RadialGradient(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY())/2,
                    new int[]{Color.RED,  Color.YELLOW, Color.BLUE}, null,
                    Shader.TileMode.MIRROR
            );

            mPaint.setShader(radialGradient);
            canvas.drawCircle(
                    mRectF.centerX(), mRectF.centerY(),
                    Math.min(mRectF.centerX(), mRectF.centerY()),
                    mPaint
            );
        }
    }

    class DrawableLinearGradientView extends PaintCanvasView{

        public DrawableLinearGradientView(Context context) {
            super(context);
            setBackground(context.getDrawable(R.drawable.gradient_linear));
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
        }
    }

    class DrawableRadialGradientView extends PaintCanvasView{

        public DrawableRadialGradientView(Context context) {
            super(context);
            setBackground(context.getDrawable(R.drawable.gradient_radial));
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
        }
    }

    class DrawableSweepGradientView extends PaintCanvasView{

        public DrawableSweepGradientView(Context context) {
            super(context);
            setBackground(context.getDrawable(R.drawable.gradient_sweep));
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
        }
    }
}
