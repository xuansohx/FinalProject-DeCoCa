package com.example.cluss;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
    Client c;
    Server s;
    DatabaseHelper dbHelper;
    DatabaseExe databaseExe;
    TextView textView;
    int car_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        System.out.println(getLocalIpAddress());
        dbHelper = new DatabaseHelper(this);
        databaseExe = new DatabaseExe(dbHelper);
        car_id = databaseExe.setting();
        try {
            s = new Server(1234);
            s.setCarid(car_id);
            c = new Client("70.12.60.110",9999,s);
            s.setClient(c);
            s.start();
            ClThread cl = new ClThread(c);
            cl.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ClThread extends Thread{
        Client client;
        public ClThread(Client client){
            this.client=client;
        }
        @Override
        public void run() {
            try {
                client.startClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void buttoncl(View v){
        s.sendMsg("1");
    }
    public void buttoncl2(View v){
        s.sendMsg("2");
    }
    public static String getLocalIpAddress() { // 디바이스의 ip 알아내기
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
