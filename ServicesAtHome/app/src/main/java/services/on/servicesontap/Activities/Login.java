package services.on.servicesontap.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;

import services.on.servicesontap.R;

public class Login extends AppCompatActivity {
LinearLayout verifyCodeLinearLayout;
TextInputLayout mobileNumberLogin;
TextView verifyNumber;
PinView userOTPCodeLogin;
ImageView backButtonImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobileNumberLogin=findViewById(R.id.mobileNumberLogin);
        verifyCodeLinearLayout=findViewById(R.id.verifyCodeLinearLayout);
        userOTPCodeLogin=findViewById(R.id.userOTPCode);
        verifyNumber=findViewById(R.id.verifyNumberLogin);
        verifyCodeLinearLayout.setVisibility(View.GONE);
        backButtonImageView=findViewById(R.id.imageBackButtonLogin);
        verifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobileNumberLogin.setError("");
                mobileNumberLogin.setErrorEnabled(false);
                String number=mobileNumberLogin.getEditText().getText().toString();
                if(!number.isEmpty())
                {
                    mobileNumberLogin.setError("");
                    mobileNumberLogin.setErrorEnabled(false);
                    if(number.length()==10)
                    {
                        mobileNumberLogin.setError("");
                        mobileNumberLogin.setErrorEnabled(false);
                        verifyCodeLinearLayout.setVisibility(View.VISIBLE);
                        String userCode= userOTPCodeLogin.getText().toString();
                        if(!userCode.isEmpty())
                        {

                        }
                        else
                        {
                            Toast.makeText(Login.this, "OTP can't be Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        mobileNumberLogin.setErrorEnabled(true);
                        mobileNumberLogin.setError("Invalid Number");
                    }




                }
                else
                {
                    mobileNumberLogin.setErrorEnabled(true);
                    mobileNumberLogin.setError("Number Can't be Empty");
                }
            }
        });
        backButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }
}