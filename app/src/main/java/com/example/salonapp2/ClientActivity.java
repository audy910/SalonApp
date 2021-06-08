package com.example.salonapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ClientActivity extends AppCompatActivity {

    private Button SubmitBtn;
    private EditText name, number, currentLength, desiredLength, previousHairTreatments, requestedStylist;
    private TextView title;
    private CheckBox extensions, haircut, color;

    private Toolbar mToolbar;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference ClientRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        mAuth = FirebaseAuth.getInstance();
        ClientRef = FirebaseDatabase.getInstance().getReference().child("Client Forms");
        
        SubmitBtn = findViewById(R.id.submitClientInfo);

        extensions =  findViewById(R.id.extensionsBox);
        haircut = findViewById(R.id.cutBox);
        color = findViewById(R.id.colorBox);


        name = findViewById(R.id.nameClient);
        number = findViewById(R.id.numberClientProfile);
        currentLength = findViewById(R.id.currentHairLength);
        desiredLength = findViewById(R.id.desiredLengthOfHair);
        previousHairTreatments = findViewById(R.id.previousTreatments);
        requestedStylist = findViewById(R.id.requestStylist);

        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);

        title = findViewById(R.id.titleHeader);
        title.setText("Client Form");
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateInfo();
            }
        });
        



    }

    private void ValidateInfo()
    {
        String Name, Number, CurrentLength, DesiredLength, PreviousHairTreatments, RequestedStylist;
        Name = name.getText().toString();
        Number = number.getText().toString();
        CurrentLength = currentLength.getText().toString();
        DesiredLength = desiredLength.getText().toString();
        PreviousHairTreatments = previousHairTreatments.getText().toString();
        RequestedStylist = requestedStylist.getText().toString();

        Boolean Haircut, Color, Extensions;
        Haircut = haircut.isChecked();
        Color = color.isChecked();
        Extensions = extensions.isChecked();

        if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Please enter name...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Number))
        {
            Toast.makeText(this, "Please enter phone number...", Toast.LENGTH_SHORT).show();
        }
        else if(phoneRegex(Number))
        {
            Toast.makeText(this, "Please enter a valid phone number...", Toast.LENGTH_SHORT).show();
        }
        else if(!Haircut && !Extensions && !Color)
        {
            Toast.makeText(this, "Please select what you would like done...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(CurrentLength)){
            Toast.makeText(this, "Please enter current length of hair...", Toast.LENGTH_SHORT).show();
        }
        else if((Haircut || Extensions) && TextUtils.isEmpty(DesiredLength)){
            Toast.makeText(this, "Please enter desired length of hair...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(PreviousHairTreatments))
        {
            Toast.makeText(this, "Please enter previous treatments. If nothing, enter N/A.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Sending Info");
            loadingBar.setMessage("Please wait while we send your info");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            savingClientInfo();
        }
    }

    private void savingClientInfo() {
        
    }

    public static Boolean phoneRegex(String phone)
    {
        if(phone.matches("[0-9]{10,15}")){
            return false;
    }        return true;

    }
}