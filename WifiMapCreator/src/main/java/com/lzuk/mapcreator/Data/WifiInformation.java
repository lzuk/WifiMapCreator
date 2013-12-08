package com.lzuk.mapcreator.Data;

import android.net.wifi.ScanResult;
import android.os.Parcelable;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class WifiInformation{
    private Integer id;
    private ScanResult scanResult;

    public WifiInformation(ScanResult scanResult)
    {
        this.scanResult= scanResult;
    }

    public WifiInformation(Integer id,ScanResult scanResult)
    {
        this.id=id;
        this.scanResult= scanResult;
    }

    public Integer getId() {return id;}

    public ScanResult getScanResult() {return scanResult;}

}
