package secura.driver.scuradriver.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import secura.driver.scuradriver.R;
import secura.driver.scuradriver.databinding.FragmentDailyBinding;


public class Daily extends Fragment {

    FragmentDailyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDailyBinding.inflate(inflater, container, false);
        binding.horizontalScrollBar.post(new Runnable() {
            @Override
            public void run() {
                binding.horizontalScrollBar.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });
        return binding.getRoot();
    }
}