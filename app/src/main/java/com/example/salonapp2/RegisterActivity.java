package com.example.salonapp2;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

        import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText UserEmail, UserPassword, UserConfirmPassword;
    private Button submit;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    String email = "";
    String password = "";
    String confirmPassword = "";
    public  void setEmail(String emailE) {
        email = emailE;
    }

    public void setPassword(String passwordE) {
        password = passwordE;
    }

    public void setConfirmPassword(String confirmPasswordE) { confirmPassword = confirmPasswordE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing variables
        mAuth = FirebaseAuth.getInstance();

        UserEmail = findViewById(R.id.emailRegister);
        UserPassword = findViewById(R.id.passwordRegister);
        UserConfirmPassword = findViewById(R.id.passwordConfirmRegister);
        submit = findViewById(R.id.submitRegister);

        loadingBar = new ProgressDialog(this);

        submit.setOnClickListener(v -> createAccount());



    }//end onCreate

    private void createAccount()
    {
        getTextInfo();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "please enter email...",Toast.LENGTH_SHORT).show();

        }
        else if(emailRegex(email))
        {
            Toast.makeText(this, "please enter valid email...",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "please enter password...",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(confirmPassword))
        {
            Toast.makeText(this, "please confirm password...",Toast.LENGTH_SHORT).show();

        }
        else if(!password.equals(confirmPassword))
        {
            Toast.makeText(this, "passwords do not match",Toast.LENGTH_SHORT).show();

        }
        else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait while account is being created");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        SendUserToStylistActivity();
                        Toast.makeText(RegisterActivity.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    } else {
                        String errorMessaage = Objects.requireNonNull(task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "An error occurred:" + errorMessaage, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }

            });
        }
    }

    private void getTextInfo() {
        email = UserEmail.getText().toString();
        password = UserPassword.getText().toString();
        confirmPassword = UserConfirmPassword.getText().toString();

    }

    private void SendUserToStylistActivity() {
        Intent StylistIntent = new Intent(RegisterActivity.this, StylistActivity.class);
        StylistIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(StylistIntent);
        finish();

    }
    public static Boolean emailRegex(String emailAddress)
    {
        if(emailAddress.matches(".*@[a-z]*[.][a-z]{2,3}")){
            return false;
        }        return true;

    }


}
