package com.lzuk.mapcreator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class Start extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ErrorReporter errReporter = new ErrorReporter();
        errReporter.Init(this);
        errReporter.CheckErrorAndSendMail(this);

        locationListener = new GPSWiFiLocationListener(getApplicationContext(), this);
        DataManager dataManager = new DataManager(this);
        locationListener.addListener(dataManager);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this,locationListener);
        locationListener.addListener(sectionsPagerAdapter);

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
}
