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
        if (enabled == false)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 100, this);
        enabled = true;
    }
    public void disableListener(){
        if (enabled == true)
            locationManager.removeUpdates(this);
        enabled = false;
    }
    public boolean isEnabled(){
        return enabled;
    }
    public Location getLastKnownLocation(){
        Criteria criteria = new Criteria();
        return locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
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
            Method method;
            for (IGPSListener listener : listenerList) {
                try {
                    method = listener.getClass().getMethod(invocationTypes.toString());
                    method.invoke(listener);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
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
        DISABLED("onDisableGPS"),
        ENABLED("onEnableGPS"),
        UPDATE("onLocationChanged");
        private final String text;
        InvocationTypes(String s) {
            text = s;
        }
        @Override
        public String toString(){
            return text;
        }
    }
}
