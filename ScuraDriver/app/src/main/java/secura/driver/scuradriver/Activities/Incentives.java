package secura.driver.scuradriver.Activities;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import secura.driver.scuradriver.Adapter.TabPagerAdapter;
import secura.driver.scuradriver.R;
import secura.driver.scuradriver.databinding.ActivityIncentivesBinding;

public class Incentives extends AppCompatActivity {
    ActivityIncentivesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncentivesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toggleColorStatusBar(Incentives.this);
        transparentStatusBar(getWindow());
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}