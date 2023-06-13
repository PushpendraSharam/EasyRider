package com.good.loginusingretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.Fragments.ForgetPassword;
import com.good.loginusingretrofit.models.ModalClass;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextInputEditText email,password;
Button login;
ProgressBar progressBar;
TextView forgetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emailMain);
        password=findViewById(R.id.passwordMain);
        login=findViewById(R.id.signInButton);
        progressBar=findViewById(R.id.progressBar);
        forgetPassword=findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPassword forget=new ForgetPassword();
                forget.show(getSupportFragmentManager(),forget.getTag());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CharSequence target=email.getText().toString();
                String pass=password.getText().toString();
                boolean result =  !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
                if(((String) target).isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "E-mail or Password Empty", Toast.LENGTH_SHORT).show();
                }else if(result==false)
                {
                    Toast.makeText(MainActivity.this, "Not a valid E-mail", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    loginCheck();

                }
            }
        });
    }
    void loginCheck()
    {
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        Call<ModalClass> call=Controler.getInstance().getApi().verifyUser(Email,Password);
        call.enqueue(new Callback<ModalClass>() {

            @Override
            public void onResponse(Call<ModalClass> call, Response<ModalClass> response) {
                ModalClass obj=response.body();
                if(obj.getSuccess()) {

                    String checkForLogin = obj.getData().getType();
                    if(checkForLogin.equals("teacher"))
                    {
                        SharedPreferences sp=getSharedPreferences("LoginPage",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("ID",obj.getData().getT_id());
                        editor.putString("Name",obj.getData().getT_name());
                        editor.putString("Type",obj.getData().getType());
                        editor.putString("Email",obj.getData().getT_email());
                        editor.putString("URL",obj.getData().getImage());
                        editor.apply();
                        Intent intent=new Intent(MainActivity.this, Home.class);
                        finishAffinity();
                        startActivity(intent);
                        finish();

                    }
                    else if(checkForLogin.equals("subadmin"))
                    {
                        SharedPreferences sp=getSharedPreferences("LoginPage",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("ID",obj.getData().getT_id());
                        editor.putString("Name",obj.getData().getT_name());
                        editor.putString("Email",obj.getData().getT_email());
                        editor.putString("Type",obj.getData().getType());
                        editor.apply();
                        Intent intent=new Intent(MainActivity.this, Admin.class);
                        finishAffinity();
                        startActivity(intent);
                        finish();

                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid E-mail or Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ModalClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
      ;

    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}