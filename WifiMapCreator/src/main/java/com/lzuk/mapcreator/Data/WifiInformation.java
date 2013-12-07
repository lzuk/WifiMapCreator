package com.lzuk.mapcreator.Data;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class WifiInformation {
    private int id;
    private String ssid;
    private int frequency;
    private int level;
    private String capabilities;


    public WifiInformation(String ssid,int frequency, int level, String capabilities)
    {
        this.ssid=ssid;
        this.frequency=frequency;
        this.level=level;
        this.capabilities=capabilities;

    }


    public String getSsid()
    {
        return ssid;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public int getLevel()
    {
        return level;
    }

    public String getCapabilities()
    {
        return capabilities;
    }




}
