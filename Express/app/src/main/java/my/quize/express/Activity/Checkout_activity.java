package my.quize.express.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import my.quize.express.R;
import my.quize.express.databinding.ActivityCheckoutBinding;

public class Checkout_activity extends AppCompatActivity {
    ActivityCheckoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());

    }
}