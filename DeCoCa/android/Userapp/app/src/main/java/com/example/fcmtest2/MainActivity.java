package com.example.fcmtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.readystatesoftware.systembartint.SystemBarTintManager;
public class MainActivity<Int> extends AppCompatActivity {
    WebView web;
    String newToken;
    private static final String ENTRY_URL = "http://70.12.60.110/DeCoCa/login.mc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult result){
                newToken = result.getToken();
                Log.e("등록 ID :", newToken);
                System.out.println(newToken.toString());
            }
        });
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FFFFFF")); //Log.e("divicetoken:", FirebaseInstanceId.getInstance().getToken().toString());
        //Log.d("divicetoken:", FirebaseInstanceId.getInstance().getToken().toString());
        //super.onCreate(savedInstanceState);
         webv();
    }
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();
        else finish();
    }
    public void webv(){
        LinearLayout layout = new LinearLayout(this);
        // layout.setOrientation(LinearLayout.HORIZONTAL);
        web = new WebView(this);
        WebSettings webSet = web.getSettings();
        webSet.setJavaScriptEnabled(true);
        webSet.setUseWideViewPort(true);
        webSet.setLoadWithOverviewMode(true);
        webSet.setBuiltInZoomControls(true);
        webSet.setAllowUniversalAccessFromFileURLs(true);
        webSet.setUseWideViewPort(true);
        webSet.setJavaScriptCanOpenWindowsAutomatically(true);
        webSet.setSupportMultipleWindows(true);
        webSet.setSaveFormData(false);
        webSet.setSavePassword(false);
        webSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println(url);
                if(url.equals(ENTRY_URL)){
                    String USERDEVICE = newToken;
                    String script = "javascript:function afterLoad(){"
                            +"document.getElementById('USERDEVICE').value='"
                            + ""+USERDEVICE+"';"
                            +"};"
                            +"afterLoad();";
                    view.loadUrl(script);
                    Log.e("ere@@@@@","여기까지오냐?");
                }

            }
        });
        web.loadUrl("http://70.12.60.110/DeCoCa");
        layout.addView(web);
        setContentView(layout);

    }
}

