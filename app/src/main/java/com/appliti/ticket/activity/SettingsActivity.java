package com.appliti.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appliti.ticket.R;
import com.appliti.ticket.util.LocalStorage;

public class SettingsActivity extends Activity {

    private EditText shopUrl;
    private EditText releaseTime;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final String receivedUrl = LocalStorage.receive(LocalStorage.SHOP_URL, "https://[URL]", SettingsActivity.this);
        final String receivedTime = LocalStorage.receive(LocalStorage.RELEASE_TIME, "6000", SettingsActivity.this);
        shopUrl = findViewById(R.id.shop_url);
        shopUrl.setText(receivedUrl);
        releaseTime = findViewById(R.id.relese_time);
        releaseTime.setText(receivedTime);

        final Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LocalStorage.storeString(LocalStorage.SHOP_URL, shopUrl.getText().toString(), SettingsActivity.this);
                LocalStorage.storeString(LocalStorage.RELEASE_TIME, releaseTime.getText().toString(), SettingsActivity.this);

                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
            }
        });
    }

}
