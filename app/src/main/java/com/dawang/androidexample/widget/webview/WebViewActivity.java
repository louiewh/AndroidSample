package com.dawang.androidexample.widget.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.dawang.androidexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WebViewActivity extends AppCompatActivity {
    private final String TAG = "WebViewActivity";

    @BindView(R.id.web_nomi)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initWebView();
    }

    protected void initWebView() {
        ButterKnife.bind(this);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

//        mWebView.loadUrl("http://www.baidu.com");
//        mWebView.loadUrl("http://tao123.com");
        mWebView.loadUrl("file:///android_asset/html/hello.html");
        mWebView.addJavascriptInterface(this, "test"); //对应js中的test.xxx
        mWebView.setWebViewClient(new WebViewClient (){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e(TAG, "onPageStarted:"+url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG, "onPageFinished:"+url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.d(TAG, "onLoadResource:"+url);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e(TAG, "onReceivedError:"+error.getDescription());
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e(TAG, "onConsoleMessage:"+consoleMessage.message());

                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e(TAG, "onProgressChanged:"+newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e(TAG, "onReceivedTitle:"+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.e(TAG, "onReceivedIcon:"+icon.toString());
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });

                b.setCancelable(false);
                b.create().show();

                return true;
            }
        });
    }

    @JavascriptInterface
    public void hello( ){
        Log.e("WebView","hello");
        Toast.makeText(this, "Js Call android", Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void hello(String msg){
        Log.e("WebView","hello");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.iv_call_js)
    void onAndroidCallJsClick(){

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript:javaCallJsWith('JavascriptInterface Javascript')");
            }
        });
    }

    @OnClick(R.id.iv_evaluate)
    void onAndroidCallJsParamClick(){

        mWebView.evaluateJavascript("javascript:javaCallJsEvaluate('Evaluate Javascript')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Toast.makeText(WebViewActivity.this, value, Toast.LENGTH_LONG).show();
            }
        });
    }

}
