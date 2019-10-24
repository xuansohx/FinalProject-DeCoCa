package com.example.pincode;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String str="";
    TextView tv;
    int carid;
    static String pincode;
    HttpTask httpTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv =findViewById(R.id.textView2);
        carid=2037;
        httpTask= new HttpTask("http://70.12.60.110/DeCoCa/getPincode.mc?carid="+carid);
        httpTask.execute();
    }
    public void bt1(View v){
        if(str.length()<6){
        str+="1";
        tv.setText(str);
        }
    }
    public void bt2(View v){
        if(str.length()<6) {
            str += "2";
            tv.setText(str);
        }
    }
    public void bt3(View v){
        if(str.length()<6) {
            str += "3";
            tv.setText(str);
        }
    }
    public void bt4(View v){
        if(str.length()<6) {
            str += "4";
            tv.setText(str);
        }
    }
    public void bt5(View v){
        if(str.length()<6) {
            str += "5";
            tv.setText(str);
        }
    }
    public void bt6(View v){
        if(str.length()<6) {
            str += "6";
            tv.setText(str);
        }
    }
    public void bt7(View v){
        if(str.length()<6) {
            str += "7";
            tv.setText(str);
        }
    }
    public void bt8(View v){
        if(str.length()<6) {
            str += "8";
            tv.setText(str);
        }
    }
    public void bt9(View v){
        if(str.length()<6) {
            str += "9";
            tv.setText(str);
        }
    }
    public void bt0(View v){
        if(str.length()<6) {
            str += "0";
            tv.setText(str);
        }
    }
    public void btMin(View v){
        int l = str.length();
        String tmp="";
        for(int i=0;i<l-1;i++){
            tmp+=str.charAt(i);
        }
        str=tmp;
        tv.setText(str);
    }
    public void btReset(View v){
        str="";
        tv.setText(str);
    }
    public void confirm(View v){
        if(str.equals(pincode)){
            tv.setText("환영합니다.");
        }
    }
}
