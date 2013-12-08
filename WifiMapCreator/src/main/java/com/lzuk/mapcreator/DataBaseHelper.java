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
    private static final String TABLE_VALUE_SSID = "SSID";
    private static final String TABLE_VALUE_FREQUENCY = "Frequency";
    private static final String TABLE_VALUE_CAPABILITIES = "Capabilities";
    private static final String TABLE_VALUE_LEVEL = "Level";
    private static final String TABLE_VALUE_X = "X";
    private static final String TABLE_VALUE_Y = "Y";
    private static final String TABLE_VALUE_Z = "Z";
    private static final String TABLE_VALUE_COORDINATES = "Coordinates";
    private static final String TABLE_VALUE_INFORMATIONS = "Informations";


    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        Log.d("create database","create1");
        String createTableData= "CREATE TABLE "+TABLE_DATA+" ( Id INTEGER PRIMARY KEY AUTOINCREMENT, Coordinates INTEGER, Informations INTEGER )";
        String createTableCoordinates= "CREATE TABLE "+TABLE_COORDINATES+" ( Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "X DOUBLE, Y DOUBLE, Z DOUBLE )";
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
        values.put(TABLE_VALUE_X,gpsCoordinates.getLatitude());
        values.put(TABLE_VALUE_Y,gpsCoordinates.getAltitude());
        values.put(TABLE_VALUE_Z,gpsCoordinates.getLongitude());
        dataBase.insert(TABLE_COORDINATES, null, values);
        idCoordinates= getLastInsertId(dataBase);
        Log.d("id",idCoordinates.toString());

        values= new ContentValues();
        values.put(TABLE_VALUE_SSID,wifiInformation.getScanResult().SSID);
        values.put(TABLE_VALUE_FREQUENCY, wifiInformation.getScanResult().frequency);
        values.put(TABLE_VALUE_LEVEL,wifiInformation.getScanResult().level);
        values.put(TABLE_VALUE_CAPABILITIES, wifiInformation.getScanResult().capabilities);
        dataBase.insert(TABLE_WIFI_INFORMATIONS,null,values);
        idInformations= getLastInsertId(dataBase);
        Log.d("id",idInformations.toString());

        if(idCoordinates>0 && idInformations>0)
        {
            values= new ContentValues();
            values.put(TABLE_VALUE_COORDINATES,idCoordinates);
            values.put(TABLE_VALUE_INFORMATIONS,idInformations);
            dataBase.insert(TABLE_DATA,null,values);
            Log.d("add..","added");
        }

        dataBase.close();
    }

    public String getAllData()
    {
        String result="";
        //String query="SELECT * FROM "+TABLE_DATA;
        String query= "SELECT Coordinates.X,Coordinates.Y, Coordinates.Z, Informations.SSID, Informations.Frequency, Informations.Capabilities ,Informations.Level FROM "+TABLE_DATA+
                " INNER JOIN "+TABLE_COORDINATES+" ON Data.Coordinates=Coordinates.id INNER JOIN "+TABLE_WIFI_INFORMATIONS+
                " ON Data.Informations=Informations.id";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do{
                result+=cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)+"\n";
            }while(cursor.moveToNext());
        }
        Log.d("result",result);
        return result;
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
