package com.cit.j_notif_es2203;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        desc = findViewById(R.id.desc);

        if (getIntent().hasExtra("msg")) {
            String msg = getIntent().getStringExtra("msg");
            desc.setText(msg);
        }


    }
}