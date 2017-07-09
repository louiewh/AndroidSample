package com.dawang.androidexample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dawang.androidexample.R;

/**
 * Created by DaWang on 2017/5/28.
 */

public abstract class PaintCanvasView extends View {
    protected Paint mPaint = new Paint();
    protected int mWidth;
    protected int mHeigth;
    protected RectF mRectF;

    public PaintCanvasView(Context context) {
        super(context);
        mRectF = new RectF();
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onChildDraw(canvas);
    }

    protected abstract void onChildDraw(Canvas canvas);

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;

        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = w;
        mRectF.bottom = h;
    }

    static public class ShadowLayerView extends PaintCanvasView {
        public ShadowLayerView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            mPaint.setTextSize(50);
            mPaint.setColor(Color.BLUE);
            mPaint.setShadowLayer(20, 0, 20, Color.GREEN);
            canvas.drawText("View Hello World", mWidth/10, mHeigth/2, mPaint);
        }
    }

    static public class TextPaintCanvasView extends PaintCanvasView{

        public TextPaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            mPaint.setTextSize(mWidth/20);
            mPaint.setColor(Color.BLUE);
            mPaint.setTypeface(Typeface.SERIF);
            canvas.drawText("View Hello World", mWidth/10, mHeigth*1/4, mPaint);

            mPaint.setTextScaleX(2);
            canvas.drawText("View Hello World", mWidth/10, mHeigth*2/4, mPaint);

            mPaint.setTextScaleX(1);
            mPaint.setTextSkewX(-1);
            canvas.drawText("View Hello World", mWidth/10, mHeigth*3/4, mPaint);
        }
    }

    public static class ShadowLayerPaintView extends PaintCanvasView {
        public ShadowLayerPaintView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            mPaint.setTextSize(mWidth/10);
            mPaint.setColor(Color.BLUE);
            mPaint.setShadowLayer(20, 0, 20, Color.GREEN);
            canvas.drawText("View Hello World", mWidth/10, mHeigth/2, mPaint);
        }
    }

    static public class LinePaintCanvasView extends PaintCanvasView{

        public LinePaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(mWidth/10);
            canvas.drawLine(mWidth/10, mHeigth/2, mWidth - mWidth/10, mHeigth/2, mPaint);
        }
    }

    static public class CapPaintCanvasView extends PaintCanvasView{

        public CapPaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            mPaint.setColor(Color.DKGRAY);
            mPaint.setStrokeWidth(50);
            canvas.drawLine(mWidth/10, mHeigth/5, mWidth - mWidth/10, mHeigth/5, mPaint);

            mPaint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(mWidth/10, mHeigth*2/5, mWidth - mWidth/10, mHeigth*2/5, mPaint);

            mPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(mWidth/10, mHeigth*3/5, mWidth - mWidth/10, mHeigth*3/5, mPaint);

            mPaint.setStrokeCap(Paint.Cap.SQUARE);
            canvas.drawLine(mWidth/10, mHeigth*4/5, mWidth - mWidth/10, mHeigth*4/5, mPaint);
        }
    }


    static public class JoinPaintCanvasView extends PaintCanvasView{

        public JoinPaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            mPaint.setColor(Color.CYAN);
            mPaint.setStrokeWidth(50);
            mPaint.setStyle(Paint.Style.STROKE);

            Path  path = new Path();
            path.moveTo(mWidth/8, mHeigth/10);
            path.lineTo(mWidth/4, mHeigth - mHeigth/8);

            mPaint.setStrokeJoin(Paint.Join.MITER);
            path.lineTo(mWidth/2, mHeigth/8);
            canvas.drawPath(path, mPaint);

            mPaint.setStrokeJoin(Paint.Join.ROUND);
            path.lineTo(mWidth*3/4, mHeigth - mHeigth/8);
            canvas.drawPath(path, mPaint);

            mPaint.setStrokeJoin(Paint.Join.BEVEL);
            path.lineTo(mWidth*7/8, mHeigth/8);
            canvas.drawPath(path, mPaint);
        }
    }


    static public class AlignPaintCanvasView extends PaintCanvasView{

        public AlignPaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            mPaint.setTextSize(mWidth/10);
            mPaint.setColor(Color.BLUE);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Align View", mWidth/2, mHeigth*1/4, mPaint);

            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Align View", mWidth/2, mHeigth*2/4, mPaint);

            mPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Align View", mWidth/2, mHeigth*3/4, mPaint);


        }
    }


    static public class ColorMatrixColorFilterView extends PaintCanvasView{
        public ColorMatrixColorFilterView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                    1, 0, 0, 0, 0,
                    0, 1, 0, 0, 0,
                    0, 0, 1, 0, 0,
                    0, 0, 0, 1, 0,
            });

            int strokeWidth = 20;
            int min = Math.min(mWidth, mHeigth);
            int radius = min/3;

            mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            mPaint.setColor(Color.argb(255, 255, 128, 103));
            canvas.drawCircle(mRectF.centerX(), mRectF.centerY(), min/2, mPaint);

            colorMatrix.setScale(0, 1f, 1f, 1f);
            mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawCircle(mWidth/3/2,   mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setColor(Color.argb(255, 255, 128, 103));
            colorMatrix.setScale(1, 0f, 0f, 1f);
            mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawCircle(mWidth*5/6, mHeigth/2, radius - strokeWidth, mPaint);
        }
    }

    static public class LightingColorFilterView extends PaintCanvasView{
        Context mContext;
        public LightingColorFilterView(Context context) {
            super(context);
            mContext = context;
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.colorfilter);
            Rect srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            int min = Math.min(mWidth, mHeigth)/3;

            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth/2 - min, mHeigth/2 - min, mWidth/2 + min, mHeigth/2 + min), mPaint);
            mPaint.setColorFilter(new LightingColorFilter(0xFF00FFFF, 0x00FF0000));
            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth/6 - min, mHeigth/2 - min, mWidth/6 + min, mHeigth/2 + min), mPaint);
            mPaint.setColorFilter(new LightingColorFilter(0xFF00FFFF, 0x00000000));
            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth*5/6 - min, mHeigth/2 - min, mWidth*5/6 + min, mHeigth/2 + min), mPaint);
        }
    }

    static public class PorterDuffColorFilterView extends PaintCanvasView{
        Context mContext;

        public PorterDuffColorFilterView(Context context) {
            super(context);
            mContext = context;
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.androidicon);
            Rect srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            int min = Math.min(mWidth, mHeigth)/3;

            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth/2 - min, mHeigth/2 - min, mWidth/2 + min, mHeigth/2 + min), mPaint);

            mPaint.setColorFilter(new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.DST));
            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth/6 - min, mHeigth/2 - min, mWidth/6 + min, mHeigth/2 + min), mPaint);

            mPaint.setColorFilter(new PorterDuffColorFilter( Color.argb(255, 255, 128, 103), PorterDuff.Mode.SRC));
            canvas.drawBitmap(bitmap, srcRect, new Rect(mWidth*5/6 - min, mHeigth/2 - min, mWidth*5/6 + min, mHeigth/2 + min), mPaint);
        }
    }

    static public class BlurMaskFilterView extends PaintCanvasView{
        // http://www.cnblogs.com/tianzhijiexian/p/4297734.html
        // http://blog.csdn.net/aigestudio/article/details/41447349
        public BlurMaskFilterView(Context context) {
            super(context);
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            int strokeWidth = 20;
            int min = Math.min(mWidth, mHeigth);
            int radius = mWidth/8;
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setColor(Color.RED);

            mPaint.setMaskFilter(new BlurMaskFilter(strokeWidth, BlurMaskFilter.Blur.NORMAL));
            canvas.drawCircle(mWidth/4/2,   mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(strokeWidth, BlurMaskFilter.Blur.SOLID));
            canvas.drawCircle(mWidth*3/4/2, mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(strokeWidth, BlurMaskFilter.Blur.OUTER));
            canvas.drawCircle(mWidth*5/4/2, mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(strokeWidth, BlurMaskFilter.Blur.INNER));
            canvas.drawCircle(mWidth*7/8, mHeigth/2, radius - strokeWidth, mPaint);
        }
    }

    static public class EmbossMaskFilterView extends PaintCanvasView{
        // http://www.cnblogs.com/tianzhijiexian/p/4297734.html
        public EmbossMaskFilterView(Context context) {
            super(context);
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            int strokeWidth = 20;
            int radius = mWidth/8;
            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setColor(Color.RED);

            mPaint.setMaskFilter(new EmbossMaskFilter(new float[] { 1, 1, 1F }, 0.1F, 8, 3));
            canvas.drawCircle(mWidth/3/2,   mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setMaskFilter(new EmbossMaskFilter(new float[] { 1, 1, -1F }, 0.1F, 8, 3));
            canvas.drawCircle(mWidth*3/3/2, mHeigth/2, radius - strokeWidth, mPaint);

            mPaint.setMaskFilter(new EmbossMaskFilter(new float[] { 0, 0, 1F }, 0.1F, 8, 3));
            canvas.drawCircle(mWidth*5/3/2, mHeigth/2, radius - strokeWidth, mPaint);
        }
    }

    static public class CirclePaintCanvasView extends PaintCanvasView{

        public CirclePaintCanvasView(Context context) {
            super(context);
        }

        @Override
        protected void onChildDraw(Canvas canvas) {

            int strokeWidth = 10;
            int min = Math.min(mWidth, mHeigth);
            int radius = min/3;

            mPaint.setStrokeWidth(strokeWidth);
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(mWidth/3/2,   mHeigth/2, radius - strokeWidth, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(mWidth/2, mHeigth/2, radius - strokeWidth, mPaint);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(mWidth*5/6, mHeigth/2, radius - strokeWidth, mPaint);
        }
    }
}