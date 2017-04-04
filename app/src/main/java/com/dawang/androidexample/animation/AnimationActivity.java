package com.dawang.androidexample.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.dawang.androidexample.R;

public class AnimationActivity extends AppCompatActivity {

    private TextView animation;
    private TextView scaleTextView;
    private ImageView roteImageView;
    private ImageView alphaImageView;
    private View setView;
    private View frameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main);

        // initLooperPrinter();

        animation = (TextView) findViewById(R.id.animation);
        initTranslateAnimation();

        scaleTextView = (TextView) findViewById(R.id.scale_animation);
        initScaleAnimation();

        roteImageView = (ImageView)findViewById(R.id.rote_animation);
        initRoteAnimation();

        alphaImageView = (ImageView)findViewById(R.id.alpha_animation);
        initAlphaAnimation();

        setView = findViewById(R.id.set_animation);
        initSetAnimation();

        frameView = findViewById(R.id.frame_animation);
        initFrameAnimation();
    }

    void initTranslateAnimation() {
        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation translateAnimation = new TranslateAnimation(0, 720, 0, 0);
                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                translateAnimation.setDuration(5000);
                animation.startAnimation(translateAnimation);
            }
        });
    }

    void initScaleAnimation() {
        scaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation scaleAnimation = new ScaleAnimation(1, 2, 1, 2);
                scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                scaleAnimation.setDuration(5000);
                scaleTextView.startAnimation(scaleAnimation);
            }
        });
    }

    void initRoteAnimation() {
        roteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation roteAnimation = new RotateAnimation(0, 360);
                roteAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                roteAnimation.setDuration(5000);
                roteImageView.startAnimation(roteAnimation);
            }
        });
    }

    void initAlphaAnimation() {
        alphaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                alphaAnimation.setDuration(5000);
                alphaImageView.startAnimation(alphaAnimation);
            }
        });
    }

    void initSetAnimation() {
        setView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation a = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set_animation);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.d("louie", "onAnimationStart");
                        Log.d("louie", Log.getStackTraceString(new Throwable()));
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("louie", "onAnimationEnd");
                        Log.d("louie", Log.getStackTraceString(new Throwable()));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.d("louie", "onAnimationRepeat");
                        Log.d("louie", Log.getStackTraceString(new Throwable()));
                    }
                });

                setView.startAnimation(a);
            }
        });
    }

    void  initFrameAnimation() {
        frameView.setBackgroundResource(R.drawable.frame_animation);
        final AnimationDrawable background = (AnimationDrawable) frameView.getBackground();

        frameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(background.isRunning()) background.stop();

                background.start();
            }
        });
    }

    void initLooperPrinter() {
        Printer mainLooperPrinter = new Printer() {
            @Override
            public void println(String x) {
                Log.d("louie", "LooperPrinter");
                Log.d("louie", Log.getStackTraceString(new Throwable()));
            }
        };

        Looper.getMainLooper().setMessageLogging(mainLooperPrinter);
    }
}
