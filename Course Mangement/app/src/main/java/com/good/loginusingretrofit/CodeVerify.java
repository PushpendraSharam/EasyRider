package com.good.loginusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.models.ReceivedOTP;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeVerify extends AppCompatActivity {
Button verify;
ImageView close;
PinView otp;
TextView resendOTP;
String code;
String id;
ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);
        code=getIntent().getStringExtra("Code");
        String userEmail=getIntent().getStringExtra("PasswordEmail");
        id=getIntent().getStringExtra("PasswordID");
        verify=findViewById(R.id.verifyCodeButton);
        close=findViewById(R.id.closeImage);
        otp=findViewById(R.id.otpCode);
        resendOTP=findViewById(R.id.resendCode);
        scrollView=findViewById(R.id.codeVeryScrollView);


        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp.setText("");
                Call<ReceivedOTP> call= Controler.getInstance().getApi().getOTP(userEmail);
                call.enqueue(new Callback<ReceivedOTP>() {

                    @Override
                    public void onResponse(Call<ReceivedOTP> call, Response<ReceivedOTP> response) {
                        ReceivedOTP data= response.body();
                        code= String.valueOf(data.getOtp());
                        Toast.makeText(CodeVerify.this, String.valueOf(data.getOtp()), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ReceivedOTP> call, Throwable t) {
                        Toast.makeText(CodeVerify.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otpCode=otp.getText().toString();
                if(otpCode.isEmpty())
                {
                    Snackbar.make(scrollView, "OTP Can't be Empty", Snackbar.LENGTH_LONG)
                            .setTextColor(getResources().getColor(R.color.white))
                            .setText("OTP Can't be Empty")
                            .setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setBackgroundTint(getResources().getColor(R.color.green))
                            .setActionTextColor(getResources().getColor(R.color.white)).show();
                }
                else if(otpCode.equals(code)) {

                    Intent intent=new Intent(CodeVerify.this, VerificationSuccess.class);
                    intent.putExtra("PasswordIDForAPI",id);
                    startActivity(intent);
                    finish();
                }
                else
                {

                    Snackbar.make(scrollView, "Invalid OTP", Snackbar.LENGTH_LONG)
                            .setTextColor(getResources().getColor(R.color.white))
                            .setText("Invalid OTP")
                            .setAction("Resend Now", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    otp.setText("");
                                    Call<ReceivedOTP> call= Controler.getInstance().getApi().getOTP(userEmail);
                                    call.enqueue(new Callback<ReceivedOTP>() {

                                        @Override
                                        public void onResponse(Call<ReceivedOTP> call, Response<ReceivedOTP> response) {
                                            ReceivedOTP data= response.body();
                                            code= String.valueOf(data.getOtp());
                                            Toast.makeText(CodeVerify.this, String.valueOf(data.getOtp()), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<ReceivedOTP> call, Throwable t) {
                                            Toast.makeText(CodeVerify.this, "Invalid Email", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            })
                            .setBackgroundTint(getResources().getColor(R.color.green))
                            .setActionTextColor(getResources().getColor(R.color.white)).show();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CodeVerify.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CodeVerify.this,MainActivity.class));
        finish();
        super.onBackPressed();
    }
}