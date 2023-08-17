package hub2.tech.easyrider.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hub2.tech.easyrider.BottomSheetPickUp;
import hub2.tech.easyrider.R;
import hub2.tech.easyrider.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    ActivityDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Displaying Navigation bar
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout,binding.toolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Opening Bottom sheet
        BottomSheetPickUp bSheet=new BottomSheetPickUp();
        bSheet.show(getSupportFragmentManager(),bSheet.getTag());
    }
}