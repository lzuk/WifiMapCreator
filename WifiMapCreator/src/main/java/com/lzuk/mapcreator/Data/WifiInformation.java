package com.lzuk.mapcreator.Data;

import android.net.wifi.ScanResult;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class WifiInformation{
    private Integer id;
    private List<ScanResult> scanResult;

    public WifiInformation(List<ScanResult> scanResult)
    {
        this.scanResult= scanResult;
    }

    public WifiInformation(Integer id, List<ScanResult> scanResult)
    {
        this.id=id;
        this.scanResult= scanResult;
    }

    public Integer getId() {return id;}

    public List<ScanResult> getScanResult() {return scanResult;}

}
