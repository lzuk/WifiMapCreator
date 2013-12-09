package com.lzuk.mapcreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 17.10.13.
 */
public class DataManager implements IGPSListener {
    //Make class singleton
    private static DataManager instance = null;
    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    protected DataManager(){

    }

    @Override
    public void onEnableGPS() {
        //nope
    }

    @Override
    public void onDisableGPS() {
        //nope
    }

    @Override
    public void onLocationChanged() {

    }
}
