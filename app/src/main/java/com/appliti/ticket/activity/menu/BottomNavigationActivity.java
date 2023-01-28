package com.appliti.ticket.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.EnterActivity;
import com.appliti.ticket.activity.ReportActivity;
import com.appliti.ticket.activity.ScanerActivity;

public abstract class BottomNavigationActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public abstract BottomNavigationView getNavigationView();

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        getNavigationView().postDelayed(() -> {
            final int itemId = item.getItemId();
            if (itemId == R.id.navigation_scan) {
                if (!getClass().equals(ScanerActivity.class)) {
                    startActivity(new Intent(this, ScanerActivity.class));
                }
            } else if (itemId == R.id.navigation_enter) {
                if (!getClass().equals(EnterActivity.class)) {
                    startActivity(new Intent(this, EnterActivity.class));
                }
            } else if (itemId == R.id.navigation_report) {
                if (!getClass().equals(ReportActivity.class)) {
                    startActivity(new Intent(this, ReportActivity.class));
                }
            }
        }, 300);
        return true;
    }
}
