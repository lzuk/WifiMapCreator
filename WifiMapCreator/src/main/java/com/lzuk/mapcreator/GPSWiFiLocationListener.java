package com.lzuk.mapcreator;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Łukasz on 17.10.13.
 */
public class GPSWiFiLocationListener implements LocationListener {
    public GPSWiFiLocationListener(Context appContext){
        applicationConetxt = appContext;
        dataManager = DataManager.getInstance();
        enabled = false;
        locationManager = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
    }
    public void enableListener(){
        if (!enabled){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 100, this);
            enabled = true;
        }
    }
    public void disableListener(){
        if (enabled){
            locationManager.removeUpdates(this);
            enabled = false;
        }

    }
    public boolean isEnabled(){
        return enabled;
    }
    public Location getLastKnownLocation(){
        return locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
    }

    //LocationListener Extend
    @Override
    public void onLocationChanged(Location location) {
        dispatchEvent(InvocationTypes.UPDATE);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(applicationConetxt, "GPS Enabled", Toast.LENGTH_SHORT).show();
        dispatchEvent(InvocationTypes.ENABLED);
    }

    @Override
    public void onProviderDisabled(String provider) {
        enabled = false;
        Toast.makeText(applicationConetxt, "GPS Disabled", Toast.LENGTH_SHORT).show();
        dispatchEvent(InvocationTypes.DISABLED);
    }
    //
    private void dispatchEvent(InvocationTypes invocationTypes){
        if (enabled) {
            for (IGPSListener listener : listenerList) {
                switch (invocationTypes) {
                    case DISABLED:{
                        listener.onDisableGPS();
                        break;
                    }
                    case ENABLED:{
                        listener.onEnableGPS();
                        break;
                    }
                    case UPDATE:{
                        listener.onLocationChanged();
                        break;
                    }
                }
            }
        }
    }
    public void addListener(IGPSListener igpsListener){
        listenerList.add(igpsListener);
    }
    private LocationManager locationManager;
    private Context applicationConetxt;
    private DataManager dataManager;
    private boolean enabled;
    private Set<IGPSListener> listenerList = new HashSet<IGPSListener>();
    enum InvocationTypes{
        DISABLED(),
        ENABLED(),
        UPDATE()
    }
}
