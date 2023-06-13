package services.on.servicesontap.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.on.servicesontap.APIs.Api;
import services.on.servicesontap.Controler.Controller;
import services.on.servicesontap.CustomClass.CommonFunctionAndClasses;
import services.on.servicesontap.ModalClass.PostalCodeResponse;
import services.on.servicesontap.ModalClass.RegisterWorkerModal;
import services.on.servicesontap.R;

public class LoginCustomer extends AppCompatActivity {
    TextView tvContinue, termsAndConditionCustomer;
    ImageView backButtonImageView;
    TextInputLayout name, email, pinCode;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    LinearLayout linearLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        tvContinue = findViewById(R.id.tvContinue);
        backButtonImageView = findViewById(R.id.imageBackButtonLoginCustomer);
        name = findViewById(R.id.nameCustomer);
        email = findViewById(R.id.emailCustomer);
        pinCode = findViewById(R.id.pinCodeCustomer);
        linearLayout = findViewById(R.id.loginCustomerLinearLayout);
        termsAndConditionCustomer = findViewById(R.id.termsAndConditionCustomer);
        progressBar = findViewById(R.id.progressBarLoginCustomer);
        progressBar.setVisibility(View.GONE);
        backButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setError("");
                name.setErrorEnabled(false);
                email.setError("");
                email.setErrorEnabled(false);
                pinCode.setError("");
                pinCode.setErrorEnabled(false);

                String varName = name.getEditText().getText().toString();
                String varEmail = email.getEditText().getText().toString();
                String varPinCode = pinCode.getEditText().getText().toString();
                if (!varName.isEmpty()) {
                    name.setError("");
                    name.setErrorEnabled(false);
                    if (!varEmail.isEmpty()) {
                        name.setError("");
                        name.setErrorEnabled(false);
                        if (varEmail.matches(emailPattern)) {
                            email.setError("");
                            email.setErrorEnabled(false);
                            if (!varPinCode.isEmpty()) {
                                pinCode.setError("");
                                pinCode.setErrorEnabled(false);
                                progressBar.setVisibility(View.VISIBLE);
                                String url = "https://api.postalpincode.in/";
                                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                                Api api = retrofit.create(Api.class);
                                Call<List<PostalCodeResponse>> pinCodeApi = api.getPostalCodeDetails(varPinCode);
                                pinCodeApi.enqueue(new Callback<List<PostalCodeResponse>>() {
                                    @Override
                                    public void onResponse(Call<List<PostalCodeResponse>> call, Response<List<PostalCodeResponse>> response) {
                                        if (response.isSuccessful()) {
                                            List<PostalCodeResponse> result = response.body();


                                            if (result != null) {

                                                Call<RegisterWorkerModal> savingUserDataApi = Controller.getInstance().getApi().registerCustomer(varName, varEmail, CommonFunctionAndClasses.UniqueUserCode);
                                                savingUserDataApi.enqueue(new Callback<RegisterWorkerModal>() {
                                                    @Override
                                                    public void onResponse(Call<RegisterWorkerModal> savingUserDataApi, Response<RegisterWorkerModal> response) {
                                                        RegisterWorkerModal obj = response.body();
                                                        if (obj.getResult().equals("-1")) {
                                                            progressBar.setVisibility(View.GONE);
                                                            snackBarMessage("Something Went Wrong");
                                                        } else if (obj.getResult().equals("-2")) {
                                                            progressBar.setVisibility(View.GONE);
                                                            email.setError("Email Already Register");

                                                        } else {
                                                            //Saving to SP so that In my profile we can get to know that user as Register
                                                            SharedPreferences sharedPreferences = getSharedPreferences("isUserRegister", MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                                            editor.putBoolean("user_exits", false);
                                                            editor.putString("user_email", varEmail);
                                                            editor.putString("user_PinCode",varPinCode);
                                                            editor.apply();
                                                            progressBar.setVisibility(View.GONE);
                                                            startActivity(new Intent(LoginCustomer.this, MainActivity.class));
                                                            finishAffinity();

                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<RegisterWorkerModal> savingUserDataApi, Throwable t) {
                                                        snackBarMessage("Network Error");
                                                    }
                                                });
                                            } else {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                pinCode.setErrorEnabled(true);
                                                pinCode.setError("Invalid PinCode ");
                                            }


                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<List<PostalCodeResponse>> call, Throwable t) {
                                        snackBarMessage("Network Error");

                                    }
                                });



                            } else {
                                pinCode.setErrorEnabled(true);
                                pinCode.setError("PinCode Can't be Empty");
                            }

                        } else {
                            email.setErrorEnabled(true);
                            email.setError("Invalid Email");
                        }

                    } else {
                        email.setErrorEnabled(true);
                        email.setError("Email Can't be Empty");
                    }
                } else {
                    name.setErrorEnabled(true);
                    name.setError("Name Can't be Empty");
                }


            }
        });
        termsAndConditionCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginCustomer.this, TermsAndCondition.class));
            }
        });
    }

    public void snackBarMessage(String message) {
        Snackbar.make(linearLayout, message, Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.white))
                .setTextColor(getResources().getColor(R.color.white))
                .setBackgroundTint(getResources().getColor(R.color.redErrorColor))
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }
}