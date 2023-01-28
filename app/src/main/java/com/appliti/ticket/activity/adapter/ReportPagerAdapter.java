package com.appliti.ticket.activity.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import com.appliti.ticket.activity.fragment.FragmentReportReleased;
import com.appliti.ticket.activity.fragment.FragmentReportWaiting;

public class ReportPagerAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    public ReportPagerAdapter(final FragmentManager fm, final int NumOfTabs) {
        super(fm);
        mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return new FragmentReportWaiting();
            case 1:
                return new FragmentReportReleased();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}