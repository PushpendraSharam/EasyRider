package services.on.servicesontap.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.navigation.NavigationView;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.on.servicesontap.Activities.ChooseOptions;
import services.on.servicesontap.Activities.PrivacyPolicy;
import services.on.servicesontap.Activities.RegisterServiceProvider;
import services.on.servicesontap.Activities.ServiceProviderView;
import services.on.servicesontap.Activities.TermsAndCondition;
import services.on.servicesontap.Adapter.IconAdapter;
import services.on.servicesontap.Adapter.RecycleViewAdapter;
import services.on.servicesontap.Controler.Controller;
import services.on.servicesontap.CustomClass.Utils;
import services.on.servicesontap.ModalClass.FetchAllDataModal;
import services.on.servicesontap.R;
import services.on.servicesontap.databinding.FragmentHomeBinding;

public class Home extends Fragment {
    FragmentHomeBinding binding;
    ActionBarDrawerToggle toggle;
    String userPinCode;
    boolean searchHint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("isUserRegister", MODE_PRIVATE);
        userPinCode = sharedPreferences.getString("user_PinCode", "302020");
        String url = "https://api.postalpincode.in/";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.originalView.setVisibility(View.GONE);
        binding.placeHolderView.setVisibility(View.VISIBLE);
        binding.loadingPlaceHolder.startShimmerAnimation();


        SharedPreferences sp = getContext().getSharedPreferences("searchHint", MODE_PRIVATE);
        searchHint = sp.getBoolean("hint", true);


        //Setting Up CheckBox

        ArrayList<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.slider_0));
        list.add(new CarouselItem(R.drawable.slider_1));
        binding.carousel.setData(list);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("isUserLOGOUT", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.putBoolean("isUserLOGOUT_", false);
        editor1.apply();

        //Adding Icons
        binding.iconRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        int[] image = {R.drawable.plumber_vector_assets, R.drawable.car_repair, R.drawable.tv_repair_vector_assets, R.drawable.painter_vector_assets, R.drawable.ac_repair_vector_assets, R.drawable.electrician_vector_assets, R.drawable.bike_repair_vector_assets, R.drawable.garder_vector_assets};
        binding.iconRecycleView.setAdapter(new IconAdapter(getContext(), Utils.skillName, image));


        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.mainActivityToolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(getActivity(), binding.originalView, binding.mainActivityToolbar, R.string.open, R.string.close);
        binding.originalView.addDrawerListener(toggle);
        toggle.syncState();

        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, Utils.skillName);
        binding.searchEditText.setAdapter(searchAdapter);
        binding.searchEditText.setThreshold(1);
        binding.searchEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ServiceProviderView.class);
                intent.putExtra("SELECT_CATEGORY", Utils.skillName[position]);
                intent.putExtra("USER_PIN_CODE", "302020");
                getContext().startActivity(intent);
            }
        });


        //RecycleView Implementing
        binding.recycleViewMainActivity.setLayoutManager(new LinearLayoutManager(getContext()));
        //Call APIs
        FetchAllDataApi();

        binding.navigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homeMenu) {
                    binding.originalView.closeDrawer(GravityCompat.START);
                }  else if (id == R.id.registerAsServiceProviderMenu) {
                    startActivity(new Intent(getContext(), RegisterServiceProvider.class));

                } else if (id == R.id.logoutMenu) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Are you sure you want to Logout?")
                            //  .setMessage("Are you sure you want to Logout?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("isUserLOGOUT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("isUserLOGOUT_", true);
                                    editor.apply();
                                    startActivity(new Intent(getContext(), ChooseOptions.class));
                                    getActivity().finishAffinity();

                                }
                            }).create().show();

                } else if (id == R.id.rateMenu) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                                getContext().getPackageName())));

                    } catch (Exception ex) {
                        startActivity(new
                                Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" +
                                getContext().getPackageName())));

                    }
                } else if (id == R.id.shareMenu) {
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, "Download the Application to get Services at your homes with one click\n\n https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
                    startActivity(Intent.createChooser(i, "Share Using"));
                } else if (id == R.id.termsAndConditionMenu) {
                    startActivity(new Intent(getContext(), TermsAndCondition.class));
                } else if (id == R.id.privacyPolicyMenu) {
                    startActivity(new Intent(getContext(), PrivacyPolicy.class));
                }
                binding.originalView.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        return binding.getRoot();
    }


    private void FetchAllDataApi() {
        Call<FetchAllDataModal> call = Controller.getInstance().getApi().getServiceProviderData(Utils.UniqueUserCode, "10");
        call.enqueue(new Callback<FetchAllDataModal>() {
            @Override
            public void onResponse(Call<FetchAllDataModal> call, Response<FetchAllDataModal> response) {
                binding.loadingPlaceHolder.stopShimmerAnimation();
                binding.placeHolderView.setVisibility(View.GONE);
                binding.originalView.setVisibility(View.VISIBLE);
                FetchAllDataModal obj = response.body();
                if (obj.getResult().equals("1")) {
                    binding.recycleViewMainActivity.setVisibility(View.VISIBLE);
                    binding.recycleViewMainActivity.setAdapter(new RecycleViewAdapter(obj.getServiceProvider(), getContext()));
                }


            }

            @Override
            public void onFailure(Call<FetchAllDataModal> call, Throwable t) {
                Log.d("API_ISSUE", t.getLocalizedMessage());
            }
        });
    }


}