package secura.driver.scuradriver.Activities;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import secura.driver.scuradriver.R;
import secura.driver.scuradriver.databinding.ActivityRewardsBinding;

public class Rewards extends AppCompatActivity {
    ActivityRewardsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRewardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toggleColorStatusBar(Rewards.this);
        transparentStatusBar(getWindow());
        binding.healthInsuranceKnowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rewards.this,KnowMoreHealth.class));
            }
        });
        binding.accientalInsuranceKnowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rewards.this,KnonMoreAccidentalInsurance.class));

            }
        });
    }
}