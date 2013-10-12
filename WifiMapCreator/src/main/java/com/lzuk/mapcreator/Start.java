package com.lzuk.mapcreator;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        header = (View)getLayoutInflater().inflate(R.layout.activity_main, null);
        headerValue = (TextView) header.findViewById(R.id.wifiString);
        refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        refresh();
    }

    public void refresh(){
        wifiNetworks = getAllWifiNetworks();
        headerValue.setText(this.wifiNetworks);
    }
    private View header;
    private TextView headerValue;
    private Button refreshButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }
    private String wifiNetworks;
    private String getAllWifiNetworks(){
        WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(true);
        }
        List<ScanResult> mScanResults = mWifiManager.getScanResults();
        this.wifiNetworks = "";
        for (ScanResult scanResult : mScanResults){
            this.wifiNetworks += scanResult.SSID + scanResult.level + "\r\n";
        }
        return "";
    }

    
}
