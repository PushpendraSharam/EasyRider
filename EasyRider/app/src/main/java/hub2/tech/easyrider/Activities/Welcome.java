package hub2.tech.easyrider.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import hub2.tech.easyrider.R;
import hub2.tech.easyrider.databinding.ActivityWelcomeBinding;

public class Welcome extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    String[] countryCode = {"+91", "+44", "+81", "+86", "+1", "+33", "+49", "+39", "+7", "+61", "+82", "+52", "+55", "+20", "+358", "+46"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        binding.numberEditText.requestFocus();

        ArrayAdapter<String> countryCodeAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_layout, countryCode);
        binding.countryCodeSpinner.setAdapter(countryCodeAdapter);
        binding.tvTerms.setText(Html.fromHtml("By creating an account, you agree to our <b>Terms and Services</b> and <b>Privacy Policy</b>"));
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,PhoneVerification.class));
            }
        });

    }
}