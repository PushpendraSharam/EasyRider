package secura.driver.scuradriver;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import secura.driver.scuradriver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.singInBtn.setBackgroundResource(R.drawable.btn_bg);
                binding.signUpBtn.setTextColor(getResources().getColor(R.color.blueColor));
                binding.singInBtn.setTextColor(getResources().getColor(R.color.white));
                binding.signUpBtn.setBackgroundResource(R.drawable.btn_login);
                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.singInBtn.setBackgroundResource(R.drawable.btn_login);
                binding.signUpBtn.setBackgroundResource(R.drawable.btn_bg);
                binding.signUpBtn.setTextColor(getResources().getColor(R.color.white));
                binding.singInBtn.setTextColor(getResources().getColor(R.color.blueColor));


                // startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });

    }


}