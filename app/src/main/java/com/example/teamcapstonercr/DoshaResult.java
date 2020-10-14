package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
@SuppressLint("SetJavaScriptEnabled")
public class DoshaResult extends AppCompatActivity {
    WebView webView;
    private int vata;
    private int pitta;
    private int kapha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosha_result);

        webView = (WebView) findViewById(R.id.webview);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        SharedPreferences prefs = getSharedPreferences("DoshaData", MODE_PRIVATE);

        vata = prefs.getInt("vata", 0);
        pitta = prefs.getInt("pitta", 0);
        kapha = prefs.getInt("kapha", 0);
        // if no saved results (first use), forward to Question
        if (vata == 0 && pitta == 0 && kapha == 0) {
            Intent intent = new Intent(getApplicationContext(), DoshaQuestion.class);
            startActivity(intent);
            // finish and destroy current activity
            finish();
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart.html");
    }

    public class WebAppInterface {

        @JavascriptInterface
        public int getVata() {
            return vata;
        }

        @JavascriptInterface
        public int getPitta() {
            return pitta;
        }

        @JavascriptInterface
        public int getKapha() {
            return kapha;
        }

    }

    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(DoshaResult.this, HomeScreen.class);
        startActivity(imgIntent);
    }

}