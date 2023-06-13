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
import com.groceries.nectar_.Fragments.ForgetPassword;

public class Loging extends AppCompatActivity {
Button logIn;
TextInputEditText email,password;
TextView sigIn;
TextView forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        toggleColorStatusBar(Loging.this);
        transparentStatusBar(getWindow());
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        logIn=findViewById(R.id.logInButton);
        sigIn=findViewById(R.id.sigIn);
        forgetPassword=findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPassword forget=new ForgetPassword();
                forget.show(getSupportFragmentManager(),forget.getTag());
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_=email.getText().toString();
                String password_=password.getText().toString();
                 if(email_.isEmpty() || password_.isEmpty())
                {
                    Toast.makeText(Loging.this, "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(password_.length()<8)
                {
                    Toast.makeText(Loging.this, "Password should contain at least 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if( Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                {
                    Intent intent=new Intent(Loging.this,MainActivity.class);
                    finishAffinity();
                    startActivity(intent);
                    finish();
                }
                else
                 {
                     Toast.makeText(Loging.this, "Invalid E-mail ", Toast.LENGTH_SHORT).show();
                 }


            }
        });
        sigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Loging.this,SignUp.class);
                finishAffinity();
                startActivity(intent);
                finish();
            }
        });
    }
}