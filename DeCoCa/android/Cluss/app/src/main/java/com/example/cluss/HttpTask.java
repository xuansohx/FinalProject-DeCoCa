package com.example.cluss;

import android.os.AsyncTask;

public class HttpTask extends AsyncTask<String,Void,String> {
        String url;
public HttpTask(String url){
        this.url = url;
        }
@Override
protected void onPreExecute() {

        }

@Override
protected String doInBackground(String... strings) {
        String str = HttpHandler.getString(url);
        System.out.println(url);
        return str;
        }

@Override
protected void onPostExecute(String str) {
        System.out.println(str);
            }
        }
