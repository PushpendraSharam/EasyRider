package hub2.tech.easyrider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hub2.tech.easyrider.R;
import hub2.tech.easyrider.databinding.ActivityPermissionsBinding;

public class Permissions extends AppCompatActivity {
    ActivityPermissionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Permissions.this, DashBoard.class));
            }
        });
    }
}