package com.lzuk.mapcreator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzuk.mapcreator.Data.GpsCoordinates;
import com.lzuk.mapcreator.Data.WifiInformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class Start extends Activity implements IGPSListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableDisableButton = (Button) findViewById(R.id.enableDisableButton);

        locationListener = new GPSWiFiLocationListener(getApplicationContext());
        locationListener.addListener(this);
        //final DataBaseHelper dataBaseHelper= new DataBaseHelper(this);
        enableDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewWiFi = (TextView) findViewById(R.id.wifiStatus);
                textViewGPS = (TextView) findViewById(R.id.GPSLocation);
               // dataBaseHelper.addData(new GpsCoordinates(10.34f,10.65f,10.87f), new WifiInformation("siec",2400,40,"Wep"));
                updateEnableDisableButton();
                if (!locationListener.isEnabled())
                    locationListener.enableListener();
                else{
                    locationListener.disableListener();
                    textViewGPS.setText(locationListener.getLastKnownLocation().toString());
                }
            }
        });
    }

    private Button enableDisableButton;
    private TextView textViewWiFi;
    private TextView textViewGPS;

    private GPSWiFiLocationListener locationListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    private void updateEnableDisableButton() {
        if (locationListener.isEnabled()){
            enableDisableButton.setText("Start");
        }
        else{
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
    public void onLocationChanged() {
        textViewGPS.setText(locationListener.getLastKnownLocation().toString());

    }
}
