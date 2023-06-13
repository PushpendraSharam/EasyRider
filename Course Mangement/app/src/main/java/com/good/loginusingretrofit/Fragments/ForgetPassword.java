package com.good.loginusingretrofit.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.good.loginusingretrofit.CodeVerify;
import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.MainActivity;
import com.good.loginusingretrofit.R;
import com.good.loginusingretrofit.models.ReceivedOTP;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPassword extends BottomSheetDialogFragment {
    TextView forgetPassword, email;
    ImageView close;
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        forgetPassword = view.findViewById(R.id.forgetPasswordButton);
        email = view.findViewById(R.id.forgetPasswordEmail);
        close = view.findViewById(R.id.backButtonInForgetActivity);
        scrollView=view.findViewById(R.id.forgetPasswordScrollView);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();


            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    Call<ReceivedOTP> call= Controler.getInstance().getApi().getOTP(email.getText().toString());
                    call.enqueue(new Callback<ReceivedOTP>() {

                        @Override
                        public void onResponse(Call<ReceivedOTP> call, Response<ReceivedOTP> response) {
                            ReceivedOTP data= response.body();

                            if(data.getStatus().equals("true"))
                            {
                                Intent intent=new Intent(getContext(), CodeVerify.class);
                                intent.putExtra("Code",String.valueOf(data.getOtp()));
                                intent.putExtra("PasswordEmail",email.getText().toString());
                                intent.putExtra("PasswordID",data.getId());
                                startActivity(intent);
                                getActivity().finish();
                            }
                            else
                            {
                                Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ReceivedOTP> call, Throwable t) {
                            Toast.makeText(getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();

                        }
                    });


                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Field Can't be empty", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(scrollView, "Field Can't be empty", Snackbar.LENGTH_SHORT)
//                            .setTextColor(getResources().getColor(R.color.white))
//                            .setText("Field Can't be empty")
//                            .setAction("Dismiss", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//
//                                }
//                            })
//                            .setBackgroundTint(getResources().getColor(R.color.green))
//                            .setActionTextColor(getResources().getColor(R.color.white)).show();

                } else {
                    Toast.makeText(getContext(), "Input Proper E-mail", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(scrollView, "Input Proper E-mail", Snackbar.LENGTH_SHORT)
//                            .setTextColor(getResources().getColor(R.color.white))
//                            .setText("Input Proper E-mail")
//                            .setAction("Dismiss", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//
//                                }
//                            })
//                            .setBackgroundTint(getResources().getColor(R.color.green))
//                            .setActionTextColor(getResources().getColor(R.color.white)).show();
                }

            }
        });
        return view;

    }


}