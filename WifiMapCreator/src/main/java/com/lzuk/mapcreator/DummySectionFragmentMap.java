package com.lzuk.mapcreator;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class DummySectionFragmentMap extends Fragment{

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;
    private GoogleMap map;
    static final LatLng PolSLAEI = new LatLng(50.28869429, 18.67747071);
    private GPSWiFiLocationListener locationListener;
    private View root;
    private WiFiListener wiFiListener;

    public DummySectionFragmentMap(FragmentActivity fragmentActivity, GPSWiFiLocationListener locationListener, WiFiListener wiFiListener) {
        this.fragmentActivity=fragmentActivity;
        this.locationListener = locationListener;
        this.wiFiListener = wiFiListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root!= null) {
            ViewGroup parent = (ViewGroup) root.getParent();
            if (parent != null)
                parent.removeView(root);
        }
        try {
            root = inflater.inflate(R.layout.fragment_main_map, container, false);
            setMap();
        } catch (InflateException e) {
        }
        return root;
    }

    private void setMap(){
        map = ((SupportMapFragment) fragmentActivity.getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setBuildingsEnabled(true);

        if (locationListener.getLastKnownLocation() == null){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(PolSLAEI, 18));
        }
        else{
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(locationListener.getLastKnownLocation().getLatitude(), locationListener.getLastKnownLocation().getLongitude()),
                    18));
        }
    }
    public void locationChanged(Location location) {
        //map.moveCamera(CameraUpdateFactory.newLatLng(
          //  new LatLng(location.getLatitude(), location.getLongitude())));

        String snippet = "";
        if (wiFiListener != null){
            for (ScanResult scanResult : wiFiListener.getScanResults()){
                snippet += scanResult.SSID + " " + scanResult.level + "\r\n";
            }
            if (getActivity() != null && getActivity().getApplicationContext() != null){
                Toast.makeText(getActivity().getApplicationContext(), "New location added", Toast.LENGTH_LONG).show();
            }
        }
        map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Test").snippet(snippet));
    }
}
