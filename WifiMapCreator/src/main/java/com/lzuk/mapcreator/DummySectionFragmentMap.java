package com.lzuk.mapcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class DummySectionFragmentMap extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private FragmentActivity fragmentActivity;

    public DummySectionFragmentMap(FragmentActivity fragmentActivity) {
        this.fragmentActivity=fragmentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_map, container, false);

        return rootView;
    }
}
