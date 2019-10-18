    package com.example.myapplication;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

    public class MainActivity extends FragmentActivity {
        private CallLogFragment callLogFragment;
        private ContactFragment contactFragment;
        private SetFragment setFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callLogFragment = new CallLogFragment();
        contactFragment = new ContactFragment();
        setFragment = new SetFragment();

       initFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {
               FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                if (tabId == R.id.tab_call_log) {
                    Log.e( "onTabReSelected: ", "tabmain");
                    transaction.replace(R.id.contentContainer,callLogFragment).commit();
                }
                else if (tabId == R.id.tab_contacts){
                    Log.e("onTabReSelected: ", "tab2");
                    transaction.replace(R.id.contentContainer,contactFragment).commit();
                }
                else if (tabId == R.id.tab_macro_setting){
                    Log.e( "onTabReSelected: ","tab3" );
                    transaction.replace(R.id.contentContainer,setFragment).commit();

                }
            }
        });
    }

        //app start view setting
    private void initFragment() {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentContainer,callLogFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        }
    }
