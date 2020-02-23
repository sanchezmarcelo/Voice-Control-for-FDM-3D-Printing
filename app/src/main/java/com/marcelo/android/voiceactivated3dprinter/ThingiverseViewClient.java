package com.marcelo.android.voiceactivated3dprinter;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ThingiverseViewClient extends WebViewClient {

    ThingiverseViewClient(WebView webView, String url){
        shouldOverrideUrlLoading(webView, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
