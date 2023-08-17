package services.on.servicesontap.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import services.on.servicesontap.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.verifyNumberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mobileNumberLogin.setError("");
                binding.mobileNumberLogin.setErrorEnabled(false);
                String number =  binding.mobileNumberLogin.getEditText().getText().toString();
                if (!number.isEmpty()) {
                    binding.mobileNumberLogin.setError("");
                    binding.mobileNumberLogin.setErrorEnabled(false);
                    if (number.length() == 10) {
                        binding.mobileNumberLogin.setError("");
                        binding.mobileNumberLogin.setErrorEnabled(false);
                        binding.verifyCodeLinearLayout.setVisibility(View.VISIBLE);
                        String userCode = binding.userOTPCode.getText().toString();
                        if (!userCode.isEmpty()) {

                        } else {
                            Toast.makeText(Login.this, "OTP can't be Empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        binding.mobileNumberLogin.setErrorEnabled(true);
                        binding.mobileNumberLogin.setError("Invalid Number");
                    }


                } else {
                    binding.mobileNumberLogin.setErrorEnabled(true);
                    binding.mobileNumberLogin.setError("Number Can't be Empty");
                }
            }
        });
        binding.imageBackButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}