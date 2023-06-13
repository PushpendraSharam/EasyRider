package com.groceries.nectar_;

import static com.groceries.nectar_.StatusBarColor.toggleColorStatusBar;
import static com.groceries.nectar_.StatusBarColor.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class LoginUsingMobile extends AppCompatActivity {
 CountryCodePicker ccp;
 ImageView codeVerify;
 ImageView back;
 TextInputEditText mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_using_mobile);
        toggleColorStatusBar(LoginUsingMobile.this);
        transparentStatusBar(getWindow());
         String code=getIntent().getStringExtra("CountryCode");
       ccp=findViewById(R.id.ccpMobile);
        codeVerify=findViewById(R.id.numberVerify);
       back=findViewById(R.id.loginUsingMobileBackButton);
       mobile=findViewById(R.id.inputMobileNumber);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onBackPressed();
           }
       });
       int code_=Integer.parseInt(code);
       ccp.setCountryForPhoneCode(code_);
       codeVerify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String number=mobile.getText().toString();
               if(number.isEmpty())
               {
                   Toast.makeText(LoginUsingMobile.this, "Field Can't be empty", Toast.LENGTH_SHORT).show();
               }
               else if(number.length()<10)
               {
                   Toast.makeText(LoginUsingMobile.this, "Invalid Number", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   startActivity(new Intent(LoginUsingMobile.this, CodeVerifyActivity.class));
               }
           }
       });

    }
}