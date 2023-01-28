package com.appliti.ticket.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.menu.BottomNavigationActivity;
import com.appliti.ticket.service.CheckCodeClient;

public class EnterActivity extends BottomNavigationActivity {

    private BottomNavigationView navigationView;
    private EditText inputEditText;
    private Button inputButton;

    @Override
    public BottomNavigationView getNavigationView() {
        return navigationView;
    }

    public EditText getInputEditText() {
        return inputEditText;
    }

    public Button getInputButton() {
        return inputButton;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.navigation_enter);

        inputEditText = (EditText) findViewById(R.id.code_input);
        inputEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView exampleView, final int actionId, final KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    performClick();
                }
                return true;
            }
        });

        inputButton = (Button) findViewById(R.id.input_btn);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                performClick();
            }
        });
    }

    private void performClick() {
        final String code = inputEditText.getText().toString();

        if (!"".equals(code)) {
            final CheckCodeClient checkCodeClient = new CheckCodeClient(EnterActivity.this, code);
            checkCodeClient.verify();
        } else {
            Toast.makeText(EnterActivity.this, "You did not enter a code!", Toast.LENGTH_LONG).show();
        }
    }
}
