package com.lzuk.mapcreator;

import android.app.Activity;
import android.net.wifi.*;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Krzysiek on 08.12.13.
 */
public class WiFiListener{

    private WifiManager wifiManager;
    Activity activity;
    private List<ScanResult> resultsWifiScan= new ArrayList<ScanResult>();

    public WiFiListener(Activity activity)
    {
        this.activity= activity;
        wifiManager = (WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
    }

    public void wifiOn()
    {
        wifiManager.setWifiEnabled(true);
    }

    public void wifiOff()
    {
        wifiManager.setWifiEnabled(false);
    }

    public List<ScanResult> getScanResults()
    {
        wifiManager.startScan();
        resultsWifiScan= wifiManager.getScanResults();
        return  resultsWifiScan;
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

}
