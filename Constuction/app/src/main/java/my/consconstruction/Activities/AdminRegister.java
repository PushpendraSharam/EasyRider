package my.consconstruction.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import my.consconstruction.R;
import my.consconstruction.databinding.ActivityAdminRegisterBinding;

public class AdminRegister extends AppCompatActivity {
ActivityAdminRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}