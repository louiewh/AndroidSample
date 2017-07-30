package com.dawang.androidexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

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
}
