package com.iteso.pdm18_scrollabletabs;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iteso.pdm18_scrollabletabs.tools.WebInterface;

public class ActivityPrivacyPolicy extends AppCompatActivity implements DialogInterface.OnKeyListener{

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        webView = findViewById(R.id.activity_privacy_policy_web_view);
        webView.loadUrl("file:///android_asset/PrivacyPolicy.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebInterface(this), "Android");
        webView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if((i == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override
    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return false;
    }

    public class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url){
            return false;
        }

    }
}
