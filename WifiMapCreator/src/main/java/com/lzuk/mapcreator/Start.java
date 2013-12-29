package com.lzuk.mapcreator;

import android.app.Activity;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.support.v4.view.ViewPager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class Start extends FragmentActivity implements IGPSListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enableDisableButton = (Button) findViewById(R.id.enableDisableButton);

        locationListener = new GPSWiFiLocationListener(getApplicationContext(), this);
        DataManager dataManager = new DataManager(this);
        locationListener.addListener(dataManager);
        locationListener.addListener(this);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this,locationListener);
        sectionsPagerAdapter.setWiFiListener(dataManager.getWiFiListener());



        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        //final GpsCoordinates gps= new GpsCoordinates(new Location("location"));
        //gps.setLatitude(20.2);
        //gps.setLongitude(30.403);
        //gps.setAltitude(50.345);

        //final WiFiListener wifi= new WiFiListener(this);

        //final DataBaseHelper dataBaseHelper= new DataBaseHelper(this);


//        enableDisableButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textViewWiFi = (TextView) findViewById(R.id.wifiStatus);
//                textViewGPS = (TextView) findViewById(R.id.GPSLocation);
//                //dodwanie do bazy
//                    //dataBaseHelper.addData(gps, new WifiInformation(wifi.getScanResults())); // dodawanie do bazy
//                    //dataBaseHelper.getAllData();// pobieranie z bazy wszystkich danych wyswietlane w logcat
//                updateEnableDisableButton();
//                if (!locationListener.isEnabled())
//                    locationListener.enableListener();
//                else{
//                    locationListener.disableListener();
//                    textViewGPS.setText(locationListener.getLastKnownLocation().toString());
//                }
//            }
//        });
    }

    private Button enableDisableButton;
    private TextView textViewWiFi;
    private TextView textViewGPS;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    private GPSWiFiLocationListener locationListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

//    private void updateEnableDisableButton() {
//        if (locationListener.isEnabled()){
//            enableDisableButton.setText("Start");
//        }
//        else{
//            enableDisableButton.setText("Stop");
//        }
//    }

    @Override
    public void onEnableGPS() {
    }

    @Override
    public void onDisableGPS() {
       // updateEnableDisableButton();
    }

    @Override
    public void onLocationChanged(Location location) {
       // textViewGPS.setText(location.toString());
    }
}
