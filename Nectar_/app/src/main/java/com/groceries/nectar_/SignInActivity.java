package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;


public class SignInActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    LinearLayout login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        toggleColorStatusBar(SignInActivity.this);
        transparentStatusBar(getWindow());
        login=findViewById(R.id.login);
        register=findViewById(R.id.resister);
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(SignInActivity.this,Loging.class);
               startActivity(intent);
           }
       });
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(SignInActivity.this,SignUp.class);
               startActivity(intent);
           }
       });

    }
}