package com.lzuk.mapcreator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lzuk.mapcreator.Data.GpsCoordinates;
import com.lzuk.mapcreator.Data.WifiInformation;

/**
 * Created by Krzysiek on 07.12.13.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WifiMapCreatorDataBase";
    private static final String TABLE_DATA = "Data";
    private static final String TABLE_COORDINATES = "Coordinates";
    private static final String TABLE_WIFI_INFORMATIONS = "Informations";
    private static final String VALUE_SSID = "SSID";
    private static final String VALUE_FREQUENCY = "Frequency";
    private static final String VALUE_CAPABILITIES = "Capabilities";
    private static final String VALUE_LEVEL = "Level";
    private static final String VALUE_X = "X";
    private static final String VALUE_Y = "Y";
    private static final String VALUE_Z = "Z";
    private static final String VALUE_COORDINATES = "Coordinates";
    private static final String VALUE_INFORMATIONS = "Informations";


    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        Log.d("create database","create1");
        String createTableData= "CREATE TABLE "+TABLE_DATA+" ( Id INTEGER PRIMARY KEY AUTOINCREMENT, Coordinates INTEGER, Informations INTEGER )";
        String createTableCoordinates= "CREATE TABLE "+TABLE_COORDINATES+" ( Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "X FLOAT, Y FLOAT, Z FLOAT )";
        String createTableInformations="CREATE TABLE "+TABLE_WIFI_INFORMATIONS+" ( Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "SSID VARCHAR(50), Frequency INTEGER , Level INTEGER, Capabilities VARCHAR(50))";

        dataBase.execSQL(createTableData);
        dataBase.execSQL(createTableCoordinates);
        dataBase.execSQL(createTableInformations);

        Log.d("create database","create2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        dataBase.execSQL("DROP TABLE IF EXISTS "+TABLE_DATA+"");

        this.onCreate(dataBase);
    }

    public void addData(GpsCoordinates gpsCoordinates, WifiInformation wifiInformation)
    {
        Integer idCoordinates;
        Integer idInformations;
        SQLiteDatabase dataBase= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(VALUE_X,gpsCoordinates.getGpsCoordinatesX());
        values.put(VALUE_Y,gpsCoordinates.getGpsCoordinatesY());
        values.put(VALUE_Z,gpsCoordinates.getGpsCoordinatesZ());
        dataBase.insert(TABLE_COORDINATES, null, values);
        idCoordinates= getLastInsertId(dataBase);
        Log.d("id",idCoordinates.toString());

        values= new ContentValues();
        values.put(VALUE_SSID,wifiInformation.getSsid());
        values.put(VALUE_FREQUENCY, wifiInformation.getFrequency());
        values.put(VALUE_LEVEL,wifiInformation.getLevel());
        values.put(VALUE_CAPABILITIES, wifiInformation.getCapabilities());
        dataBase.insert(TABLE_WIFI_INFORMATIONS,null,values);
        idInformations= getLastInsertId(dataBase);
        Log.d("id",idInformations.toString());

        if(idCoordinates>0 && idInformations>0)
        {
            values= new ContentValues();
            values.put(VALUE_COORDINATES,idCoordinates);
            values.put(VALUE_INFORMATIONS,idInformations);
            dataBase.insert(TABLE_DATA,null,values);
            Log.d("add..","added");
        }

        dataBase.close();
    }

    private Integer getLastInsertId(SQLiteDatabase dataBase)
    {
        Integer id;
        String query="SELECT last_insert_rowid()";
        Cursor cursor= dataBase.rawQuery(query,null);

        if (cursor.moveToFirst())
        {
            do{
               id = Integer.parseInt(cursor.getString(0));
            }while(cursor.moveToNext());
        }else return -1;
        return id;
    }
}
