package com.lzuk.mapcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class DummySectionFragmentWiFi extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;
    private TextView textViewWiFi;

    public DummySectionFragmentWiFi(FragmentActivity fragmentActivity) {
        this.fragmentActivity=fragmentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_wifi, container, false);
        textViewWiFi = (TextView) rootView.findViewById(R.id.wifiStatus);

        return rootView;
    }

    public void setTextViewWiFi(String text)
    {
        textViewWiFi.setText(text);
    }


}