package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebInterface {
    Context context;

    public WebInterface (Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String toastText){
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
    }
}
