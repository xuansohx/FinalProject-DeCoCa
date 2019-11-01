package com.example.DeCoCar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cardiomood.android.controls.gauge.BatteryIndicatorGauge;
import com.cardiomood.android.controls.gauge.SpeedometerGauge;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ContactFragment extends Fragment {
    static SpeedometerGauge speedometer;
    static BatteryIndicatorGauge batteryindicator;
    static int kph = 200;
    static int btr = 80;
    Button  sosbutton;
    static TextView textView, textView2,btrtextView,datetextView,temptextView;
    SeekBar seekBar;
    ToggleButton toggleButton;
    ImageView imageView,doorimageView;
    Handler handler = new Handler();
    static ProcessThread thread ;
    String[] output = {"0","0","0","0","0"};



    class ProcessThread extends Thread {
        ProcessHandler processHandler = new ProcessHandler();

        public void run() {
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler {
            public void handleMessage(Message msg) {
                output = (String[]) msg.obj;

                handler.post(new Runnable() {
                    public void run() {
                        speedometer.setSpeed(Double.parseDouble(output[0]), 2000, 300);
                        textView.setText(Double.parseDouble(output[0]) + " " + "kmh");
                        batteryindicator.setValue((float) Double.parseDouble(output[1]), 500, 500);
                        btrtextView.setText((float) Double.parseDouble(output[1])+" "+"%");
                        temptextView.setText("현재온도 : "+" "+Double.parseDouble(output[3])+" "+"C'");
                        seekBar.setProgress((int) Double.parseDouble(output[3]));
                        textView2.setText(Double.parseDouble(output[3])+" "+"C'");
                        if(output[2].equals("0")) {
                            doorimageView.setImageResource(R.drawable.lock);
                        }else {
                            doorimageView.setImageResource(R.drawable.openlock);
                        }
                    }
                });
            }
        }
    }
    public ContactFragment() {
        thread = new ProcessThread();
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_contact, container, false);
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        datetextView=view.findViewById(R.id.datetextView);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        btrtextView=view.findViewById(R.id.btrtextView);
        temptextView= view.findViewById(R.id.temptextView);
        imageView=view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.topview);
        doorimageView=view.findViewById(R.id.doorimageView);
        speedometer = (SpeedometerGauge) view.findViewById(R.id.speedometer);
        seekBar = view.findViewById(R.id.seekBar);
        sosbutton = view.findViewById(R.id.sosbutton);
        ShowTimeMethod();
        // Inflate the layout for this fragment
        seekBar.setMax(30);
        seekBar.setMin(18);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                textView2.setText(progress + " " + "C'");
                MainActivity.s.sendMsg("t"+progress);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainActivity.s.sendMsg("1");
            }
        });
        doorimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.s.sendMsg("2");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainActivity.s.sendMsg("1");
            }
        });
       speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
            @Override
            public String getLabelFor(double progress, double maxProgress) {
                return String.valueOf((int) Math.round(progress));
            }
        });
        // configure value range and ticks
        speedometer.setMaxSpeed(300);
        speedometer.setMajorTickStep(5);
        speedometer.setMinorTicks(2);
        // Configure value range colors
        speedometer.addColoredRange(30, 140, Color.GREEN);
        speedometer.addColoredRange(140, 180, Color.YELLOW);
        speedometer.addColoredRange(180, 400, Color.RED);
        batteryindicator = (BatteryIndicatorGauge) view.findViewById(R.id.batteryindicator);
        sosbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:01041478192"));
                try {
                    c.startActivity(intent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        @SuppressLint("ResourceType") final View inflate = getMenuInflater().inflate(R.menu.menu_main, (ViewGroup) menu);
        return true;
    }
    private LayoutInflater getMenuInflater() {
        return null;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowTimeMethod() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                datetextView.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }
        };
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    handler.sendEmptyMessage(1);
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
    public static void uitest(){
        String [] arr = new String [4];
        String input = MainActivity.s.Serverkph+"";
        String btr = MainActivity.s.btr+"";
        String door =MainActivity.s.door+"";
        String temp =MainActivity.s.temp+"";
        arr[0] = input;
        arr[1]=btr;
        arr[2]=door;
        arr[3]=temp;
        Message message = Message.obtain();
        message.obj=arr;
        thread.processHandler.sendMessage(message);
    }
}










