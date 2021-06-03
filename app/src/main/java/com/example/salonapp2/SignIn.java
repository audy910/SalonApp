package com.example.salonapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    //declaring variables
    private TextView NeedAccount;
    private EditText email, password;
    private Button submit;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //initializing Variables
        NeedAccount = findViewById(R.id.registerLink);
        email = findViewById(R.id.emailSignIn);
        password = findViewById(R.id.passwordSignIn);
        submit = findViewById(R.id.submitSignIn);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        //checks if clicking need an account
        NeedAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegister();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
    }

    private void LogIn() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if(TextUtils.isEmpty(emailText))
        {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(passwordText))
        {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait while we log you in");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(task -> {

                            if(task.isSuccessful())
                            {
                                SendUserToMain();
                                Toast.makeText(this, "you are logged in", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else{
                                String message = Objects.requireNonNull(task.getException().getMessage());
                                Toast.makeText(this, "Error Occured: "+message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }


                    });
        }
    }

    private void SendUserToMain() {
        Intent StylistIntent = new Intent(SignIn.this, StylistActivity.class);
        StylistIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(StylistIntent);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void SendUserToRegister() {
        Intent RegisterIntent = new Intent(SignIn.this, RegisterActivity.class);
        RegisterIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(RegisterIntent);
        finish();
    }
}