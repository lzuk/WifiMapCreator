package com.lzuk.mapcreator.Data;

import android.location.Location;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class GpsCoordinates extends Location {

    private Integer id;


    public GpsCoordinates(Location location)
    {
        super(location);
    }


    public GpsCoordinates(Integer id,Location location)
    {
        super(location);
        this.id=id;
    }

    public Integer getId()
    {
        return id;
    }

}

