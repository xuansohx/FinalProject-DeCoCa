    package com.example.DeCoCar;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import static com.example.DeCoCar.ContactFragment.btr;
import static com.example.DeCoCar.ContactFragment.kph;

    public class MainActivity extends FragmentActivity {
        private CallLogFragment callLogFragment;
        private  ContactFragment contactFragment;
        private SetFragment setFragment;
        static Client c;
        static Server s;
        static String eTime = "";
        static boolean init = false;
        DatabaseHelper dbHelper;
        DatabaseExe databaseExe;
        int car_id;
        double lat, lng;
        static boolean end = false;
        static boolean ch = false;
        static int btr = 80;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            System.out.println("IP is" + getLocalIpAddress());
            dbHelper = new DatabaseHelper(this);
            databaseExe = new DatabaseExe(dbHelper);
            car_id = databaseExe.setting();
            Log.i("car_id in main", car_id + "");
            try {
                s = new Server(1234);
                s.setCarid(car_id);
                c = new Client("70.12.60.110", 9999, s);
                c.carid = car_id;
                s.setClient(c);
                s.start();
                ClThread cl = new ClThread(c);
                cl.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            callLogFragment = new CallLogFragment();
            contactFragment = new ContactFragment();
            setFragment = new SetFragment();
            Runnable r2 = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                        SimpleDateFormat format3 = new SimpleDateFormat("mm");
                        Calendar time = Calendar.getInstance();
                        String hour = format2.format(time.getTime());
                        String minute = format3.format(time.getTime());
                        if (eTime.equals(hour) && !ch) {
                            end = true;
                            System.out.println("시간이 되었다 !@#!@#!@#");
                            ch = true;
                        }
                    }
                }
            };
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        while (!end) {
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("다이알로그 온");
                                dialog();
                            }
                        });
                        end = false;
                    }
                }
            };
            new Thread(r).start();
            new Thread(r2).start();
            String[] permissions = {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE
            };

            ActivityCompat.requestPermissions(this, permissions, 101);
            int check = PermissionChecker.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (check == PermissionChecker.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Yes Permission", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No Permission", Toast.LENGTH_SHORT).show();
            }
            initFragment();
            BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
            bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

                @Override
                public void onTabSelected(@IdRes int tabId) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    if (tabId == R.id.tab_call_log) {

                        transaction.replace(R.id.contentContainer, callLogFragment).commit();
                    } else if (tabId == R.id.tab_contacts) {

                        transaction.replace(R.id.contentContainer, contactFragment).commit();
                    } else if (tabId == R.id.tab_macro_setting) {

                        transaction.replace(R.id.contentContainer, setFragment).commit();
                    }
                }
            });


           // Thread t = new Thread(signal);
          //  t.start();
        }//app start view setting
        private void initFragment() {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentContainer, callLogFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

        public static String getLocalIpAddress() { // 디바이스의 ip 알아내기
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
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
        class ClThread extends Thread {
            Client client;

            public ClThread(Client client) {
                this.client = client;
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

        public void dialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("데꼬가");
            builder.setMessage("도착");
            builder.setIcon(R.drawable.car);
            builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 사용자가 완료 버튼을 누르면 s.status 의 문에 해당하는 녀석을 열고 ecu 에 해당하는 녀석에게 문을 열라고 시킨다.
                    Toast.makeText(MainActivity.this, "이용해주셔서 감사합니다.", Toast.LENGTH_SHORT).show();
                    s.sendEndtoServer();
                }
            });
            LayoutInflater inflater = getLayoutInflater();
            View tview = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.tLayout));
            builder.setView(tview);
            TextView view = tview.findViewById(R.id.textView);
            view.setText("목적지에 도착했습니다\n 완료를 누르시면 문이 열립니다.");
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        }
        public void startDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("데꼬가");
            builder.setMessage("출발");
            builder.setIcon(R.drawable.car);
            builder.setPositiveButton("시작", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // 사용자가 완료 버튼을 누르면 s.status 의 문에 해당하는 녀석을 열고 ecu 에 해당하는 녀석에게 문을 열라고 시킨다.
                    Toast.makeText(MainActivity.this, "일정이 시작되었습니다.", Toast.LENGTH_SHORT).show();
                    s.sendEndtoServer();
                }
            });
            LayoutInflater inflater = getLayoutInflater();
            View tview = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.tLayout));
            builder.setView(tview);
            TextView view = tview.findViewById(R.id.textView);
            view.setText("차량이 도착했습니다\n 시작을 누르시면 출발합니다.");
            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        }


    }
