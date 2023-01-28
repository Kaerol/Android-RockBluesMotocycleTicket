package com.appliti.ticket.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.adapter.ReportPagerAdapter;
import com.appliti.ticket.activity.menu.BottomNavigationActivity;
import com.appliti.ticket.service.ReportClient;
import com.appliti.ticket.service.response.Product;
import com.appliti.ticket.service.response.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends BottomNavigationActivity {

    protected BottomNavigationView navigationView;

    public void setReport(Report report) {
        this.report = report;
    }

    private Report report;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

        setContentView(R.layout.activity_report);

        navigationView = findViewById(R.id.report_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.navigation_report);

        tabLayout = findViewById(R.id.report_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.report_waiting));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.report_released));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void getData() {
        final ReportClient reportClient = new ReportClient(ReportActivity.this);
        reportClient.get();
    }

    public List<Product> getWaitingData() {
        if (report != null) {
            return report.getWaiting();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Product> getReleasedData() {
        if (report != null) {
            return report.getReleased();
        } else {
            return new ArrayList<>();
        }
    }

    public void initView() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.report_pager);
        final ReportPagerAdapter adapter = new ReportPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {

            }
        });
    }
}