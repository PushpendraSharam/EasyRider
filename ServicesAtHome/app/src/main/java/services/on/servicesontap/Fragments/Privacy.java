package services.on.servicesontap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import services.on.servicesontap.CustomClass.Utils;
import services.on.servicesontap.R;


public class Privacy extends Fragment {
    TextView privacy;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_privacy, container, false);
        privacy=view.findViewById(R.id.tv_privacy);
        privacy.setText(Utils.privacy);
        return view;
    }
}