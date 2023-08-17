package services.on.servicesontap.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.on.servicesontap.APIs.Api;
import services.on.servicesontap.Controler.Controller;
import services.on.servicesontap.CustomClass.Utils;
import services.on.servicesontap.ModalClass.PostalCodeResponse;
import services.on.servicesontap.ModalClass.RegisterWorkerModal;
import services.on.servicesontap.R;
import services.on.servicesontap.databinding.ActivityLoginCustomerBinding;

public class LoginCustomer extends AppCompatActivity {
   ActivityLoginCustomerBinding binding;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageBackButtonLoginCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.nameCustomer.setError("");
                binding.nameCustomer.setErrorEnabled(false);
                binding.emailCustomer.setError("");
                binding.emailCustomer.setErrorEnabled(false);
                binding.pinCodeCustomer.setError("");
                binding.pinCodeCustomer.setErrorEnabled(false);

                String varName =  binding.nameCustomer.getEditText().getText().toString();
                String varEmail =  binding.emailCustomer.getEditText().getText().toString();
                String varPinCode =  binding.pinCodeCustomer.getEditText().getText().toString();
                if (!varName.isEmpty()) {
                    binding.nameCustomer.setError("");
                    binding.nameCustomer.setErrorEnabled(false);
                    if (!varEmail.isEmpty()) {
                        binding.nameCustomer.setError("");
                        binding.nameCustomer.setErrorEnabled(false);
                        if (varEmail.matches(emailPattern)) {
                            binding.emailCustomer.setError("");
                            binding.emailCustomer.setErrorEnabled(false);
                            if (!varPinCode.isEmpty()) {
                                binding.pinCodeCustomer.setError("");
                                binding.pinCodeCustomer.setErrorEnabled(false);
                                binding.progressBarLoginCustomer.setVisibility(View.VISIBLE);
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

                                                Call<RegisterWorkerModal> savingUserDataApi = Controller.getInstance().getApi().registerCustomer(varName, varEmail, Utils.UniqueUserCode);
                                                savingUserDataApi.enqueue(new Callback<RegisterWorkerModal>() {
                                                    @Override
                                                    public void onResponse(Call<RegisterWorkerModal> savingUserDataApi, Response<RegisterWorkerModal> response) {
                                                        RegisterWorkerModal obj = response.body();
                                                        if (obj.getResult().equals("-1")) {
                                                            binding.progressBarLoginCustomer.setVisibility(View.GONE);
                                                            snackBarMessage("Something Went Wrong");
                                                        } else if (obj.getResult().equals("-2")) {
                                                            binding.progressBarLoginCustomer.setVisibility(View.GONE);
                                                            binding.emailCustomer.setError("Email Already Register");

                                                        } else {
                                                            //Saving to SP so that In my profile we can get to know that user as Register
                                                            SharedPreferences sharedPreferences = getSharedPreferences("isUserRegister", MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                                            editor.putBoolean("user_exits", false);
                                                            editor.putString("user_email", varEmail);
                                                            editor.putString("user_PinCode",varPinCode);
                                                            editor.apply();
                                                            binding.progressBarLoginCustomer.setVisibility(View.GONE);
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
                                                binding.progressBarLoginCustomer.setVisibility(View.INVISIBLE);
                                                binding.pinCodeCustomer.setErrorEnabled(true);
                                                binding.pinCodeCustomer.setError("Invalid PinCode ");
                                            }


                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<List<PostalCodeResponse>> call, Throwable t) {
                                        snackBarMessage("Network Error");

                                    }
                                });



                            } else {
                                binding.pinCodeCustomer.setErrorEnabled(true);
                                binding.pinCodeCustomer.setError("PinCode Can't be Empty");
                            }

                        } else {
                            binding.emailCustomer.setErrorEnabled(true);
                            binding.emailCustomer.setError("Invalid Email");
                        }

                    } else {
                        binding.emailCustomer.setErrorEnabled(true);
                        binding.emailCustomer.setError("Email Can't be Empty");
                    }
                } else {
                    binding.nameCustomer.setErrorEnabled(true);
                    binding.nameCustomer.setError("Name Can't be Empty");
                }


            }
        });
        binding.termsAndConditionCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginCustomer.this, TermsAndCondition.class));
            }
        });
    }

    public void snackBarMessage(String message) {
        Snackbar.make(binding.loginCustomerLinearLayout, message, Snackbar.LENGTH_SHORT)
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