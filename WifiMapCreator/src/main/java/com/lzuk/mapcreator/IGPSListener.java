package com.lzuk.mapcreator;

import android.location.Location;

/**
 * Created by Łukasz on 17.10.13.
 */
public interface IGPSListener {
    void onEnableGPS();
    void onDisableGPS();
    void onLocationChanged(Location location);
}
