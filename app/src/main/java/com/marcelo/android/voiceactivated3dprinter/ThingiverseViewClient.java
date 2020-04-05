package com.marcelo.android.voiceactivated3dprinter;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ThingiverseViewClient extends WebViewClient {

    ThingiverseViewClient(WebView webView, String url){
        shouldOverrideUrlLoading(webView, url);


    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setNeedInitialFocus(true);
        view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        view.getSettings().setAppCacheEnabled(true);
        view.getSettings().getLoadsImagesAutomatically();


        return true;
    }
}
