package com.dawang.androidexample.hook;

import android.content.ClipData;
import android.content.Context;
import android.os.IBinder;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author louie.wang
 * @Date 2018/5/2
 * @Description
 */

@RunWith(AndroidJUnit4.class)
public class BinderHook {
    private static final String TAG = "BinderHook";

    public static void hookClipboardService() throws Exception {


        // 下面这一段的意思实际就是: ServiceManager.getService("clipboard");
        // 只不过 ServiceManager这个类是@hide的
        Class<?> serviceManager = Class.forName("android.os.ServiceManager");
        Method getService = serviceManager.getDeclaredMethod("getService", String.class);

        // ServiceManager里面管理的原始的Clipboard Binder对象
        // 一般来说这是一个Binder代理对象
        IBinder rawBinder = (IBinder) getService.invoke(null, Context.CLIPBOARD_SERVICE);

        // Hook 掉这个Binder代理对象的 queryLocalInterface 方法
        // 然后在 queryLocalInterface 返回一个IInterface对象, hook掉我们感兴趣的方法即可.
        IBinder hookedBinder = (IBinder) Proxy.newProxyInstance(serviceManager.getClassLoader(),
                new Class<?>[] { IBinder.class },
                new BinderProxyHookHandler(rawBinder));

        // 把这个hook过的Binder代理对象放进ServiceManager的cache里面
        // 以后查询的时候 会优先查询缓存里面的Binder, 这样就会使用被我们修改过的Binder了
        Field cacheField = serviceManager.getDeclaredField("sCache");
        cacheField.setAccessible(true);
        Map<String, IBinder> cache = (Map) cacheField.get(null);
        cache.put(Context.CLIPBOARD_SERVICE, hookedBinder);
    }

    static class BinderProxyHookHandler implements InvocationHandler {
        private IBinder mRawIBinder;

        public BinderProxyHookHandler(IBinder iBinder) {
            mRawIBinder = iBinder;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 把剪切版的内容替换为 "you are hooked"
            if ("getPrimaryClip".equals(method.getName())) {
                Log.d(TAG, "hook getPrimaryClip");
                return ClipData.newPlainText(null, "you are hooked");
            }

            // 欺骗系统,使之认为剪切版上一直有内容
            if ("hasPrimaryClip".equals(method.getName())) {
                return true;
            }

            return null;
        }
    }

}
