package com.lzuk.mapcreator;

import com.lzuk.mapcreator.Data.GpsCoordinates;

/**
 * Created by Łukasz on 17.10.13.
 */
public interface IGPSListener {
    void onEnableGPS();
    void onDisableGPS();
    void onLocationChanged(GpsCoordinates gpsCoordinates);
}
