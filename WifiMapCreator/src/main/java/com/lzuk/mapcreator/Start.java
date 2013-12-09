package com.lzuk.mapcreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzuk.mapcreator.Data.GpsCoordinates;

public class Start extends Activity implements IGPSListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableDisableButton = (Button) findViewById(R.id.enableDisableButton);

        locationListener = new GPSWiFiLocationListener(getApplicationContext(), this);
        dataManager = new DataManager(this);
        locationListener.addListener(dataManager);
        locationListener.addListener(this);

        enableDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewWiFi = (TextView) findViewById(R.id.wifiStatus);
                textViewGPS = (TextView) findViewById(R.id.GPSLocation);
                updateEnableDisableButton();
                if (!locationListener.isEnabled())
                    locationListener.enableListener();
                else {
                    locationListener.disableListener();
                    textViewGPS.setText(locationListener.getLastKnownLocation().toString());
                }
            }
        });
    }

    private Button enableDisableButton;
    private TextView textViewWiFi;
    private TextView textViewGPS;
    private DataManager dataManager;

    private GPSWiFiLocationListener locationListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    private void updateEnableDisableButton() {
        if (locationListener.isEnabled()) {
            enableDisableButton.setText("Start");
        } else {
            enableDisableButton.setText("Stop");
        }
    }

    @Override
    public void onEnableGPS() {
    }

    @Override
    public void onDisableGPS() {
        updateEnableDisableButton();
    }

    @Override
    public void onLocationChanged(GpsCoordinates gpsCoordinates) {
        textViewGPS.setText(gpsCoordinates.toString());
        if (dataManager.wifiInformation != null) {
            textViewWiFi.setText(dataManager.wifiInformation.toString());
        }
    }
}
