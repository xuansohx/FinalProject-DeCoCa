package com.example.cluss;

import android.os.Bundle;
import android.view.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(getLocalIpAddress());

        try {
            s = new Server(1234);
            s.start();
            c = new Client("70.12.60.110",9999,s);
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
