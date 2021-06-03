package com.example.salonapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class StylistActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);

        title = findViewById(R.id.titleHeader);
        title.setText("STYLIST");
//        mToolbar.setLogo(ContextCompat.getDrawable(StylistActivity.this, R.drawable.icon));
        Objects.requireNonNull(getSupportActionBar()).setTitle("");


    }


}