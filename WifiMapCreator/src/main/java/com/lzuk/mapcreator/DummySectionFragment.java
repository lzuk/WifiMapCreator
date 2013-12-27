package com.lzuk.mapcreator;

import android.location.Location;
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
public class DummySectionFragment extends Fragment implements IGPSListener{

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;
    private Button enableDisableButton;
    private TextView textViewWiFi;
    private TextView textViewGPS;
    private GPSWiFiLocationListener locationListener;

    public DummySectionFragment(FragmentActivity fragmentActivity) {
        this.fragmentActivity=fragmentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

        locationListener = new GPSWiFiLocationListener(fragmentActivity.getApplicationContext(), fragmentActivity);
        DataManager dataManager = new DataManager(fragmentActivity);

        enableDisableButton = (Button) rootView.findViewById(R.id.enableDisableButton);

        enableDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewWiFi = (TextView) rootView.findViewById(R.id.wifiStatus);
                textViewGPS = (TextView) rootView.findViewById(R.id.GPSLocation);
                //dodwanie do bazy
                //dataBaseHelper.addData(gps, new WifiInformation(wifi.getScanResults())); // dodawanie do bazy
                //dataBaseHelper.getAllData();// pobieranie z bazy wszystkich danych wyswietlane w logcat
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

    private void updateEnableDisableButton() {
        if (locationListener.isEnabled()){
            enableDisableButton.setText("Start");
        }
        else{
            enableDisableButton.setText("Stop");
        }
    }

    @Override
    public void onEnableGPS() {

    }

    @Override
    public void onDisableGPS() {
        updateEnableDisableButton();
    }

    @Override
    public void onLocationChanged(Location location) {
        textViewGPS.setText(location.toString());
    }
}
