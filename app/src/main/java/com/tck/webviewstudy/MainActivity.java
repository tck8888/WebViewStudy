package com.tck.webviewstudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        tvText = (TextView) findViewById(R.id.tv_text);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new JsToAndroid(), "JsToAndroid");
        webView.loadUrl("file:///android_asset/index.html");

    }

    public class JsToAndroid {
        /**
         * 获取app的token
         *
         * @return token
         */
        @JavascriptInterface
        public String h5GetAppToken() {
            return "JWT "+System.currentTimeMillis();
        }

        /**
         * 获取资讯id
         *
         * @return id
         */
        @JavascriptInterface
        public int h5GetInformationId() {
            return 10;
        }

        @JavascriptInterface
        public void jsCallAndroidArgs(final String args) {
            final String temp  = args;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvText.setText(temp+System.currentTimeMillis());
                }
            });

        }

    }
}
