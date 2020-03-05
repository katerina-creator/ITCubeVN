package com.example.it_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CallSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_activity_school);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        final String data = intent.getDataString();

        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            final String jobId = data.substring(data.lastIndexOf("/") + 1);
        }
    }
}
