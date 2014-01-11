package com.lzuk.mapcreator;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class DummySectionFragment extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;
    private Button enableDisableButton;
    private TextView textViewGPS;
    private GPSWiFiLocationListener locationListener;

    public DummySectionFragment(FragmentActivity fragmentActivity, GPSWiFiLocationListener locationListener) {
        this.fragmentActivity=fragmentActivity;
        this.locationListener = locationListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //locationListener = new GPSWiFiLocationListener(fragmentActivity.getApplicationContext(), fragmentActivity);

        enableDisableButton = (Button) rootView.findViewById(R.id.enableDisableButton);

        enableDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewGPS = (TextView) rootView.findViewById(R.id.GPSLocation);

                updateEnableDisableButton();
                if (!locationListener.isEnabled())
                    locationListener.enableListener();
                else{
                    locationListener.disableListener();
                    textViewGPS.setText(locationListener.getLastKnownLocation().toString());
                }
            }
        });

        return rootView;
    }

    public void updateEnableDisableButton() {
        if (locationListener.isEnabled()){
            enableDisableButton.setText("Start");
        }
        else{
            enableDisableButton.setText("Stop");
        }

    }

    public void setGPSText(Location location)
    {
        textViewGPS.setText(location.toString());
    }

}
