package com.example.salonapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private Button StylistLogInBtn,ClientLogin,OwnerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up Buttons
        StylistLogInBtn = findViewById(R.id.StylistLogInBtn);
        ClientLogin = findViewById(R.id.ClientLogInBtn);
        OwnerLogin = findViewById(R.id.OwnerLogInBtn);
        //setting up toolbar name
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign In As");

        //stylist onClick listener
        StylistLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLogInActivity();
            }
        });
        //client onClick listener
        ClientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToClientActivity();
            }
        });

    }

    //method to send to stylist login page
    private void SendUserToLogInActivity() {
        Intent loginIntent = new Intent(MainActivity.this, SignIn.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
    //method to send to client login page
    private void SendUserToClientActivity() {
        Intent ClientLoginIntent = new Intent(MainActivity.this, ClientActivity.class);
        ClientLoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ClientLoginIntent);
        finish();
    }
//    //method to send to owner login page
//    private void SendUserToOwnerLoginActivity() {
//        Intent OwnerLogin = new Intent(MainActivity.this, .class);
//        OwnerLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(OwnerLogin);
//        finish();
//    }
}