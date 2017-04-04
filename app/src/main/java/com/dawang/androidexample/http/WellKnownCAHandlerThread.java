package com.dawang.androidexample.http;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by DaWang on 2017/4/4.
 */

public class WellKnownCAHandlerThread extends HandlerThread {

    private Handler  mViewHandler;
    public WellKnownCAHandlerThread(String name, Handler handler) {
        super(name);
        mViewHandler = handler;
    }

    public void postHttpsConnect(){
        Handler handler = new Handler(getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("https://www.baidu.com/");
//                    url = new URL("https://wikipedia.org");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                SSLContext sslContext = null;
                try {
                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType)  {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }}, new SecureRandom());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            HostnameVerifier verifier = HttpsURLConnection.getDefaultHostnameVerifier();
                            Boolean result = verifier.verify("*.baidu.*", session);
                            Log.e("https", session.toString());
                            Log.e("https", "WellKnown Https Certificate HostnameVerifier!");
                            return true;
                        }
                    };

                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                    urlConnection.setHostnameVerifier(hostnameVerifier);
                    urlConnection.connect();

                    Log.e("https", "WellKnown Https Certificate Success!");
                    Log.e("https", urlConnection.getResponseCode() + "OK");
                    if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        Log.e("https", "WellKnown Https Certificate Success!");
                        Message msg = Message.obtain();
                        msg.what = HttpsActivity.HTTPS_SUCCES_WellKnown;
                        mViewHandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
