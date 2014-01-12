package com.lzuk.mapcreator;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.InflateException;
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
import java.util.logging.Logger;

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
    private View root;

    public DummySectionFragmentWiFi(FragmentActivity fragmentActivity) {
        this.fragmentActivity=fragmentActivity;
        wiFiListener = new WiFiListener(fragmentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root!= null) {
            ViewGroup parent = (ViewGroup) root.getParent();
            if (parent != null)
                parent.removeView(root);
        }
        try {
            root = inflater.inflate(R.layout.fragment_main_wifi, container, false);
            prepareFragment();
        } catch (InflateException e) {
        }
        return root;
    }
    private void prepareFragment(){
        textViewWiFi = (TextView) root.findViewById(R.id.wifiStatus);
        wifiListView = (ListView) root.findViewById(R.id.wifiListView);
        button = (Button)root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWiFiList(wiFiListener.getScanResults());
            }
        });
    }
    public void setWiFiList(List<ScanResult> scans){
        ArrayList<String> scansL = new ArrayList<String>();
        StringBuilder result ;
        for (ScanResult scanResult : scans){
            result = new StringBuilder();
            result.append("Nazwa : " + scanResult.SSID);
            result.append("\n");
            result.append("Szyfrowanie : " + scanResult.capabilities );
            result.append("\n");
            result.append("Poziom : " + scanResult.level + " dB");
            result.append("\t");
            result.append("Częstotliwość : " + scanResult.frequency + " MHz");
            scansL.add(result.toString());
        }
        adapter = new ArrayAdapter<String>(fragmentActivity, R.layout.row, scansL);
        wifiListView.setAdapter(adapter);
    }
}