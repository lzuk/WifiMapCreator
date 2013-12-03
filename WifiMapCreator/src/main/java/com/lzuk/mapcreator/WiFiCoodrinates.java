package com.lzuk.mapcreator;

import android.location.Location;
import android.net.wifi.ScanResult;

import java.util.List;

/**
 * Created by Łukasz on 17.10.13.
 */
public class WiFiCoodrinates {
    public WiFiCoodrinates(List<ScanResult> wifiScanResults, Location location){
        this.wifiScanResults = wifiScanResults;
        this.location = location;
    }

    private List<ScanResult> wifiScanResults;
    private Location location;
    private boolean sentToServer = false;

    public List<ScanResult> getWifiScanResults() {
        return wifiScanResults;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isSentToServer() {
        return sentToServer;
    }

    public void setAsSentToServer() {
        this.sentToServer = true;
    }
}
