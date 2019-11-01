package com.example.DeCoCar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;


public class SetFragment extends Fragment {


    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);

        LinearLayout layout = view.findViewById(R.id.setLayout);

        WebView web = new WebView(getActivity());
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
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://70.12.60.110/DeCoCa");
        layout.addView(web);

        //getActivity().setContentView(layout);



        return view;

    }


}
