package com.lzuk.mapcreator;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class DummySectionFragmentWiFi extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;
    private TextView textViewWiFi;
    private ListView wifiListView;
    private Button button;
    private WiFiListener wiFiListener;
    private ArrayAdapter<String> adapter;

    public DummySectionFragmentWiFi(FragmentActivity fragmentActivity) {
        this.fragmentActivity=fragmentActivity;
        wiFiListener = new WiFiListener(fragmentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_wifi, container, false);
        textViewWiFi = (TextView) rootView.findViewById(R.id.wifiStatus);
        wifiListView = (ListView) rootView.findViewById(R.id.wifiListView);
        button = (Button)rootView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    setWiFiList(wiFiListener.getScanResults());
                 }catch (Exception e){
                }
            }
        });


        return rootView;
    }

    public void setWiFiList(List<ScanResult> scans){
        ArrayList<String> scansL = new ArrayList<String>();
        for (ScanResult scanResult : scans){
            scansL.add(scanResult.SSID + ": " + scanResult.level);
        }
        adapter = new ArrayAdapter<String>(fragmentActivity, R.layout.row, scansL);
        wifiListView.setAdapter(adapter);
    }
}