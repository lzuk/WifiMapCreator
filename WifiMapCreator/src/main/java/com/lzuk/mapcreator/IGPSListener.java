package com.lzuk.mapcreator;

/**
 * Created by Łukasz on 17.10.13.
 */
public interface IGPSListener {
    void onEnableGPS();
    void onDisableGPS();
    void onLocationChanged();
}
