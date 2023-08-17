package secura.driver.scuradriver.Activities;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import secura.driver.scuradriver.Fragments.RuppeFragment;
import secura.driver.scuradriver.HomeFragment;
import secura.driver.scuradriver.R;
import secura.driver.scuradriver.SignIn;
import secura.driver.scuradriver.databinding.ActivityDeshBoardBinding;

public class Dashboard extends AppCompatActivity {
    ActivityDeshBoardBinding binding;
    ActionBarDrawerToggle toggle;

    HomeFragment homeFragment;
    RuppeFragment ruppeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeshBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View header = binding.navigationBar.getHeaderView(0);

        homeFragment=new HomeFragment();
        ruppeFragment=new RuppeFragment();

        //default fragment
        fragload(homeFragment);

        binding.dashNevbuttons.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.nav_home)
                {
                    fragload(homeFragment);
                }
                else if (id==R.id.nav_ruppe)
                {
                    fragload(ruppeFragment);
                }
                return true;
            }
        });

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
    private void fragload(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout,fragment).addToBackStack(null).commit();
    }

}