package com.lzuk.mapcreator;

import android.app.Activity;
import android.widget.Toast;

import com.lzuk.mapcreator.Data.GpsCoordinates;
import com.lzuk.mapcreator.Data.WifiInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 17.10.13.
 */
public class DataManager implements IGPSListener {
    //Make class singleton
    private static DataManager instance = null;
    public static DataManager getInstance(Activity activity){
        if (instance == null){
            instance = new DataManager(activity);
        }
        return instance;
    }
    protected DataManager(Activity activity){
        this.activity = activity;
        wiFiListener = new WiFiListener(activity);
        dataBaseHelper = new DataBaseHelper(activity);
    }
    private Activity activity;
    private WiFiListener wiFiListener;
    private DataBaseHelper dataBaseHelper;

    @Override
    public void onEnableGPS() {
        //nope
    }

    @Override
    public void onDisableGPS() {
        //nope
    }

    @Override
    public void onLocationChanged(GpsCoordinates gpsCoordinates) {
        dataBaseHelper.addData(gpsCoordinates, new WifiInformation(wiFiListener.getScanResults()));
        dataBaseHelper.getAllData();
    }
}
