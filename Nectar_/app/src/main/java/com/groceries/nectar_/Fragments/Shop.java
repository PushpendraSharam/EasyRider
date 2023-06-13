package com.groceries.nectar_.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.groceries.nectar_.R;
import com.groceries.nectar_.RecycleViewAdapter.ExclusiveOffers;
import com.groceries.nectar_.RecycleViewAdapter.Groceries;

import java.util.ArrayList;

public class Shop extends Fragment {
    ImageSlider imageSlider;
    RecyclerView offersRecycleView,bestSellingRecycleView,groceriesRecycleView,beefRecycleView;
    int []offerImage={R.drawable.banana,R.drawable.apple_,R.drawable.banana,R.drawable.apple_,R.drawable.banana,R.drawable.apple_};
    String name[]={"Organic Bananas","Red Apple","Organic Bananas","Red Apple","Organic Bananas","Red Apple"};
    String price[]={"$4","$6.34","$4.99","$6.34","$4.99","$6.34"};
    int []bestSellingImages={R.drawable.ginger,R.drawable.red_chili,R.drawable.ginger,R.drawable.red_chili,R.drawable.ginger,R.drawable.red_chili};
    String bestName[]={"Ginger","Red Capsicum","Ginger","Red Capsicum","Ginger","Red Capsicum"};
    String bestPrice[]={"$7","$2.56","$7.31","$2.56","$7.31","$2.56"};

    int []beefImages={R.drawable.beef,R.drawable.boil_chiken,R.drawable.beef,R.drawable.boil_chiken,R.drawable.beef,R.drawable.boil_chiken,};
    String beefNames[]={"Beef Bone","Broiler chicken","Beef Bone","Broiler chicken","Beef Bone","Broiler chicken",};
    String beefPrice[]={"$5","$6.24","$5.99","$6.24","$5.99","$6.24",};
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop, container, false);
        imageSlider=view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.sss, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sfdsf, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sss, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sfdsf, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        offersRecycleView=view.findViewById(R.id.exclusiveOffersRecycleView);
        bestSellingRecycleView=view.findViewById(R.id.bestSellingRecycleView);
        groceriesRecycleView=view.findViewById(R.id.groceriesRecycleView);
        beefRecycleView=view.findViewById(R.id.beefRecycleView);

        offersRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        offersRecycleView.setAdapter(new ExclusiveOffers(offerImage,name,price,getContext()));

        bestSellingRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        bestSellingRecycleView.setAdapter(new ExclusiveOffers(bestSellingImages,bestName,bestPrice,getContext()));

        groceriesRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        groceriesRecycleView.setAdapter(new Groceries());

        beefRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        beefRecycleView.setAdapter(new ExclusiveOffers(beefImages,bestName,bestPrice,getContext()));

        return view;
    }
}