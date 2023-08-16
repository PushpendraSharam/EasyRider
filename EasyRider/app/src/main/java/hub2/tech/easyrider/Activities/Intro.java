package hub2.tech.easyrider.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hub2.tech.easyrider.Adapters.SliderAdapter;
import hub2.tech.easyrider.R;
import hub2.tech.easyrider.databinding.ActivityIntroBinding;


public class Intro extends AppCompatActivity {
    ActivityIntroBinding binding;
    TextView dots[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.sliderViewPager.setAdapter(new SliderAdapter(this));
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getItem(0) < 2) {
                    binding.sliderViewPager.setCurrentItem(getItem(1), true);
                } else {
                    startActivity(new Intent(Intro.this, Welcome.class));
                    finish();
                }

            }
        });
        setUpIndicators(0);
        binding.sliderViewPager.addOnPageChangeListener(listener);
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intro.this, Welcome.class));
                finish();
            }
        });

    }

    void setUpIndicators(int position) {
        dots = new TextView[3];
        binding.indicatorLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(Intro.this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            binding.indicatorLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.app_color, getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicators(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    int getItem(int i) {
        return binding.sliderViewPager.getCurrentItem() + i;
    }
}