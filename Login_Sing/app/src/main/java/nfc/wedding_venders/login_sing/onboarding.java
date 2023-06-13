package nfc.wedding_venders.login_sing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class onboarding extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    Button skipBtn, nextBtn, backBtn;
    TextView[] dots;
    LinearLayout dLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        viewPager = findViewById(R.id.sliderViewPager);
        skipBtn = findViewById(R.id.skipBtn);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.backBtn);
        dLinearLayout = findViewById(R.id.dLinear);
        adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) > 0) {
                    viewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 3) {
                    viewPager.setCurrentItem(getItem(1), true);
                } else {
                    startActivity(new Intent(onboarding.this, login.class));

                }
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(onboarding.this, login.class));

            }
        });
        setUpIndicators(0);
        viewPager.addOnPageChangeListener(listener);

    }

    public void setUpIndicators(int position) {
        dots = new TextView[4];
        dLinearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            dLinearLayout.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicators(position);
            if (position > 0) {
                backBtn.setVisibility(View.VISIBLE);
            } else {
                backBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

}