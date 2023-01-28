package com.appliti.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.menu.BottomNavigationActivity;
import com.appliti.ticket.service.CheckCodeClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanerActivity extends BottomNavigationActivity {

    private BottomNavigationView navigationView;

    @Override
    public BottomNavigationView getNavigationView() {
        return navigationView;
    }

    public Button getScanBtn() {
        return scanBtn;
    }

    public TextView getmTextMessage() {
        return mTextMessage;
    }

    private Button scanBtn;
    private TextView mTextMessage;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaner);

        final Activity activity = this;

        mTextMessage = (TextView) findViewById(R.id.message);
        scanBtn = (Button) findViewById(R.id.scan_btn);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.navigation_scan);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("SCAN QR Code");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled scanning ", Toast.LENGTH_LONG).show();
            } else {
                final Pattern pattern = Pattern.compile("(\\w+)=?([^&]+)?");
                final Matcher matcher = pattern.matcher(result.getContents());
                final Map<String, String> params = new HashMap<>();
                while (matcher.find()) {
                    params.put(matcher.group(1), matcher.group(2));
                }
                final CheckCodeClient checkCodeClient = new CheckCodeClient(this, params);
                checkCodeClient.verify();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
