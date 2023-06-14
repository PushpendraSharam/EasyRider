package secura.driver.scuradriver.Activities;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import secura.driver.scuradriver.R;
import secura.driver.scuradriver.SignIn;
import secura.driver.scuradriver.databinding.ActivityDeshBoardBinding;

public class Dashboard extends AppCompatActivity {
    ActivityDeshBoardBinding binding;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeshBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toggleColorStatusBar(Dashboard.this);
        transparentStatusBar(getWindow());
        setSupportActionBar(binding.toolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View header = binding.navigationBar.getHeaderView(0);

        LinearLayout incentives = header.findViewById(R.id.incentivesBtn);
        LinearLayout rewards = header.findViewById(R.id.rewards);
        incentives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Incentives.class));
            }
        });
        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Rewards.class));
            }
        });

    }
}