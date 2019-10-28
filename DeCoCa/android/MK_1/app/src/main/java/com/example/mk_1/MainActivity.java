package com.example.mk_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cardiomood.android.controls.gauge.BatteryIndicatorGauge;
import com.cardiomood.android.controls.gauge.SpeedometerGauge;
import com.cardiomood.android.controls.progress.CircularProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SpeedometerGauge speedometer;

    private BatteryIndicatorGauge batteryindicator;
    int kph=100;
    Button button;
    TextView textView,textView2;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        speedometer = (SpeedometerGauge) findViewById(R.id.speedometer);
        seekBar=findViewById(R.id.seekBar);
        button = findViewById(R.id.button);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView2.setText(progress+" "+"C'");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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




        batteryindicator = (BatteryIndicatorGauge) findViewById(R.id.batteryindicator);





  //      CircularProgressBar circ = (CircularProgressBar) findViewById(R.id.circularprogress);
    //    circ.setProgress(50, 1000);



    }

    public void clickBt(View view){
        batteryindicator.setValue(kph, 2000, 500);
        speedometer.setSpeed(180, 2000, 300);
        textView.setText(kph+" "+"kmh");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}