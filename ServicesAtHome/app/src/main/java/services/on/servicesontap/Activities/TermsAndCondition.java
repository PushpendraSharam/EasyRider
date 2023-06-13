package services.on.servicesontap.Activities;

import static services.on.servicesontap.CustomClass.CommonFunctionAndClasses.terms;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import services.on.servicesontap.R;

public class TermsAndCondition extends AppCompatActivity {
   Toolbar toolbar;
   TextView tvTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        toolbar=findViewById(R.id.termsAndConditionToolbar);
        tvTerms=findViewById(R.id.tv_termsAndCondition);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
              tvTerms.setText(terms);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
