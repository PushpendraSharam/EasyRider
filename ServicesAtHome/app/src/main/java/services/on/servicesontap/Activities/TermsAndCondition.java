package services.on.servicesontap.Activities;

import static services.on.servicesontap.CustomClass.Utils.terms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import services.on.servicesontap.databinding.ActivityTermsAndConditionBinding;

public class TermsAndCondition extends AppCompatActivity {
    ActivityTermsAndConditionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTermsAndConditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.termsAndConditionToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
              binding.tvTermsAndCondition.setText(terms);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
