package com.lzuk.mapcreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 17.10.13.
 */
public class DataManager {
    public void addData(WiFiCoodrinates wiFiCoodrinates){
        datas.add(wiFiCoodrinates);

    }
    public int getDataSize(){
        return datas.size();
    }
    private List<WiFiCoodrinates> datas = new ArrayList<WiFiCoodrinates>();

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
}
