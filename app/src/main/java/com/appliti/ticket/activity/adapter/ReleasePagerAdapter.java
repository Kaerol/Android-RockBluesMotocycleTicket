package com.appliti.ticket.activity.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import com.appliti.ticket.activity.fragment.FragmentReleaseComments;
import com.appliti.ticket.activity.fragment.FragmentReleaseData;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ReleasePagerAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    public ReleasePagerAdapter(final FragmentManager fm, final int NumOfTabs) {
        super(fm);
        mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return new FragmentReleaseData();
            case 1:
                return new FragmentReleaseComments();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}