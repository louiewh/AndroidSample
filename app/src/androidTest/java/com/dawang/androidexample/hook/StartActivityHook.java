package com.dawang.androidexample.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.dawang.androidexample.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author louie.wang
 * @Date 2018/5/2
 * @Description
 */

@RunWith(AndroidJUnit4.class)
public class StartActivityHook extends ActivityTestRule<MainActivity>{

    public StartActivityHook() {
        super(MainActivity.class);
    }

    @Test
    public void startMainActivity() throws Exception {
        attachContext();
        Context appContext = InstrumentationRegistry.getTargetContext();

        Intent intent = new Intent(appContext, MainActivity.class);
        appContext.startActivity(intent);

        Thread.sleep(1000*10);
    }

    public void attachContext() throws Exception{
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        // 创建代理对象
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, evilInstrumentation);
    }

    static public class EvilInstrumentation extends Instrumentation {

        private static final String TAG = "EvilInstrumentation";

        // ActivityThread中原始的对象, 保存起来
        Instrumentation mBase;

        public EvilInstrumentation(Instrumentation base) {
            mBase = base;
        }

        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) {

            // Hook之前, XXX到此一游!
            Log.d(TAG, "\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                    "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                    "\ntarget = [" + target + "], \nintent = [" + intent +
                    "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");

            // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
            // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
            try {
                Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                        "execStartActivity",
                        Context.class, IBinder.class, IBinder.class, Activity.class,
                        Intent.class, int.class, Bundle.class);
                execStartActivity.setAccessible(true);
                return (ActivityResult) execStartActivity.invoke(mBase, who,
                        contextThread, token, target, intent, requestCode, options);
            } catch (Exception e) {
                // 某该死的rom修改了  需要手动适配
                throw new RuntimeException("do not support!!! pls adapt it");
            }
        }
    }
}
