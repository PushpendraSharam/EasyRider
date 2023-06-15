package secura.driver.scuradriver.Activities;

import static secura.driver.scuradriver.Common.toggleColorStatusBar;
import static secura.driver.scuradriver.Common.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import secura.driver.scuradriver.R;

public class KnonMoreAccidentalInsurance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knon_more_accidental_insurance);
        toggleColorStatusBar(KnonMoreAccidentalInsurance.this);
        transparentStatusBar(getWindow());
    }
}