package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class SignUp extends AppCompatActivity {
Button signUp;
    CountryCodePicker ccp;
    TextView login;

TextInputEditText userName,email,password,userNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        toggleColorStatusBar(SignUp.this);
//        transparentStatusBar(getWindow());
        signUp=findViewById(R.id.signUpButton);
        userName=findViewById(R.id.userName);
        email=findViewById(R.id.userEmail);
        password=findViewById(R.id.userPassword);
        ccp=findViewById(R.id.ccpMobile);
        userNumber=findViewById(R.id.inputMobileNumber);
        login=findViewById(R.id.LoginUsingSignIN);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,Loging.class);
                finishAffinity();
                startActivity(intent);
                finish();
            }
        });
        ccp.setCountryForPhoneCode(91);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName_=userName.getText().toString();
                String email_=email.getText().toString();
                String password_=password.getText().toString();
                String userNumber_=userNumber.getText().toString();
                if(userName_.isEmpty()||email_.isEmpty()||password_.isEmpty()||userNumber_.isEmpty())
                {
                    Toast.makeText(SignUp.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if (password_.length()<8)
                {
                    Toast.makeText(SignUp.this, "Password should contain at least 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if(Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                {
                    Intent intent=new Intent(SignUp.this,CodeVerifyActivity.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(SignUp.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
               
            }
        });
    }
}