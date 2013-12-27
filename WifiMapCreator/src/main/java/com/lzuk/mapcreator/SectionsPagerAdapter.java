package com.lzuk.mapcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by Krzysiek on 27.12.13.
 */
public class SectionsPagerAdapter  extends FragmentPagerAdapter {

    FragmentActivity fragmentActivity;

    public SectionsPagerAdapter(FragmentManager fm, FragmentActivity fragmentActivity) {
        super(fm);
        this.fragmentActivity=fragmentActivity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();

        switch (position)
        {
            case 0:
                fragment = new DummySectionFragment(fragmentActivity);
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new DummySectionFragmentWiFi(fragmentActivity);
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new DummySectionFragmentMap(fragmentActivity);
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment.setArguments(args);
                break;
            default:
                fragment = new Fragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return fragmentActivity.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return fragmentActivity.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return fragmentActivity.getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }
}
