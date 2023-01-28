package com.appliti.ticket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.appliti.ticket.R;

public class ForbiddenActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forbidden);

        final Button goToLogin = findViewById(R.id.login);

        goToLogin.setOnClickListener(view -> startActivity(new Intent(ForbiddenActivity.this, LoginActivity.class)));
    }

}
