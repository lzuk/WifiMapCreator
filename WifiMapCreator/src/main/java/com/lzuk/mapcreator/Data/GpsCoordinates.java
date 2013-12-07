package com.lzuk.mapcreator.Data;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class GpsCoordinates {

    private Float id;
    private Float x;
    private Float y;
    private Float z;

    public GpsCoordinates(Float x, Float y, Float z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public GpsCoordinates(Float id,Float x, Float y, Float z)
    {
        this.id=id;
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Float getGpsCoordinatesX()
    {
        return x;
    }

    public Float getGpsCoordinatesY()
    {
        return y;
    }

    public Float getGpsCoordinatesZ()
    {
        return z;
    }

    public Float getId()
    {
        return id;
    }

}

