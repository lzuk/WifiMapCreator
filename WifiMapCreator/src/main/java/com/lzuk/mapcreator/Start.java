package com.lzuk.mapcreator;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class Start extends FragmentActivity implements IGPSListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ErrorReporter errReporter = new ErrorReporter();
        errReporter.Init(this);
        errReporter.CheckErrorAndSendMail(this);


        //enableDisableButton = (Button) findViewById(R.id.enableDisableButton);

        locationListener = new GPSWiFiLocationListener(getApplicationContext(), this);
        DataManager dataManager = new DataManager(this);
        locationListener.addListener(dataManager);
        locationListener.addListener(this);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this,locationListener);
        sectionsPagerAdapter.setWiFiListener(dataManager.getWiFiListener());



        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

    }

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    private GPSWiFiLocationListener locationListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

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
