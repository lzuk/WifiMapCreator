package com.lzuk.mapcreator;

import android.app.Activity;
import android.location.Location;

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
    public void onLocationChanged(Location location) {
        dataBaseHelper.addData(location, wiFiListener.getScanResults(),false);
        //dataBaseHelper.getAllData();
    }

    public WiFiListener getWiFiListener()
    {
        return wiFiListener;
    }
}
