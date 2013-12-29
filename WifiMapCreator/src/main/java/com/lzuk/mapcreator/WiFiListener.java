package com.lzuk.mapcreator;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysiek on 08.12.13.
 */
public class WiFiListener {

    private WifiManager wifiManager;
    Activity activity;
    private List<ScanResult> resultsWifiScan = new ArrayList<ScanResult>();

    public WiFiListener(Activity activity) {
        this.activity = activity;
        wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
    }

    public void wifiOn() {
        wifiManager.setWifiEnabled(true);
    }

    public void wifiOff() {
        wifiManager.setWifiEnabled(false);
    }

    public List<ScanResult> getScanResults() {
        wifiManager.startScan();
        resultsWifiScan = wifiManager.getScanResults();
        return resultsWifiScan;
    }

    public String getWifiStateStr() {

        switch (wifiManager.getWifiState()) {
            case 0:
                return "disabling";
            case 1:
                return "disabled";
            case 2:
                return "enabling";
            case 3:
                return "enabled";
            default:
                return "unknown";
        }
    }

    @Override
    public String toString()
    {
        String data="";
        List<ScanResult> results = getScanResults();
        for (int i =0; i< results.size();i++) {
            data+= "SSID "+results.get(i).SSID.toString()+"\n ";
            data+= "BSSID "+results.get(i).BSSID.toString()+"\n ";
            data+="frequency "+ results.get(i).frequency+"\n ";
            data+= "level "+results.get(i).level+"\n ";
            data+= "capabilities "+results.get(i).capabilities.toString()+"\n\n";
            //data+= "timestamp "+results.get(i).timestamp+"\n ";
        }
        return  data;
    }

}
