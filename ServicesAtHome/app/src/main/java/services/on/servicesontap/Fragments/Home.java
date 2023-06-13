package services.on.servicesontap.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.on.servicesontap.APIs.Api;
import services.on.servicesontap.Activities.ChooseOptions;
import services.on.servicesontap.Activities.MyProfile;
import services.on.servicesontap.Activities.PrivacyPolicy;
import services.on.servicesontap.Activities.RegisterServiceProvider;
import services.on.servicesontap.Activities.TermsAndCondition;
import services.on.servicesontap.Adapter.RecycleViewAdapter;
import services.on.servicesontap.Controler.Controller;
import services.on.servicesontap.CustomClass.CommonFunctionAndClasses;
import services.on.servicesontap.ModalClass.FetchAllDataModal;
import services.on.servicesontap.ModalClass.PostalCodeResponse;
import services.on.servicesontap.R;

public class Home extends Fragment {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    RecyclerView recyclerView;
    ImageCarousel carousel;
    CheckBox othersCheckBox;
    ImageView searchImageView, noRecordImageView;
    NestedScrollView nestedScrollView;
    TextView tvNoRecord, tvTryAgainLater, toolbar_title;
    LinearLayout noRecordLinearLayout;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    String customerCity = "Jaipur";
    TextView tv_customer_city;
    String userPinCode;
    String city = "Jaipur";
    BottomNavigationView bottomBar;
    ShimmerFrameLayout loadingPlaceHolder;
    boolean searchHint;
    int REQUESTCODEFORPERMISSION = 111;
    boolean locationPermissionGranted;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
//        getLocationPermission();
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
//                            List<Address> addresses = null;
//                            try {
//                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                            String pinCode = addresses.get(0).getPostalCode();
//                            String cityName = addresses.get(0).getLocality();
//
//                            for (int i = 0; i < CommonFunctionAndClasses.allCityName.length; i++)
//                                if (cityName.equals(CommonFunctionAndClasses.allCityName[i])) {
//                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("CustomerCity", MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putString("city", CommonFunctionAndClasses.allCityName[i]);
//                                    editor.apply();
//                                    customerCity=CommonFunctionAndClasses.allCityName[i];
//                                    String markYourNeedString = "In " + customerCity;
//                                    tv_customer_city.setText(markYourNeedString);
//                                    toolbar_title.setText(customerCity);
//
//                                    Log.d("Location1", pinCode + cityName);
//                                    SharedPreferences sp = getContext().getSharedPreferences("searchHint", MODE_PRIVATE);
//                                    searchHint = sp.getBoolean("hint", false);
//                                    break;
//                                }
//
//
//                        }
//                    }
//                });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("isUserRegister", MODE_PRIVATE);
        userPinCode = sharedPreferences.getString("user_PinCode", "302020");
        Toast.makeText(getContext(), userPinCode, Toast.LENGTH_SHORT).show();

        String url = "https://api.postalpincode.in/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<PostalCodeResponse>> call = api.getPostalCodeDetails(userPinCode);
        call.enqueue(new Callback<List<PostalCodeResponse>>() {
            @Override
            public void onResponse(Call<List<PostalCodeResponse>> call, Response<List<PostalCodeResponse>> response) {
                if (response.isSuccessful()) {
                    List<PostalCodeResponse> result = response.body();


                    if (result != null) {

                        try {
                            customerCity = result.get(0).getPostOffice().get(0).getName() + ", " + result.get(0).getPostOffice().get(0).getDistrict();
                            city = result.get(0).getPostOffice().get(0).getDistrict();
                            toolbar_title.setText(customerCity);

                        } catch (Exception e) {
                            Log.d("PinCode Error", e.getMessage());
                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<List<PostalCodeResponse>> call, Throwable t) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sp = getContext().getSharedPreferences("searchHint", MODE_PRIVATE);
        searchHint = sp.getBoolean("hint", true);


        //finding Ids
        navigationView = view.findViewById(R.id.navigationMenu);
        toolbar = view.findViewById(R.id.mainActivityToolbar);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        bottomBar = view.findViewById(R.id.bottomBar);
        toolbar_title = view.findViewById(R.id.toolbar_title);
        recyclerView = view.findViewById(R.id.recycleViewMainActivity);
        loadingPlaceHolder = view.findViewById(R.id.loadingPlaceHolder);
        nestedScrollView = view.findViewById(R.id.nestedScrollVIew);
        othersCheckBox = view.findViewById(R.id.othersNeedsCheckBox);
        searchImageView = view.findViewById(R.id.searchImageView);
        noRecordLinearLayout = view.findViewById(R.id.noRecordLinearLayout);
        noRecordImageView = view.findViewById(R.id.noRecordImageView);
        tvTryAgainLater = view.findViewById(R.id.tvTryAgainLater);
        tv_customer_city = view.findViewById(R.id.tv_customer_city);
        carousel = view.findViewById(R.id.carousel);
        tvNoRecord = view.findViewById(R.id.tvNoRecord);
        checkBox1 = view.findViewById(R.id.checkBox1);
        checkBox2 = view.findViewById(R.id.checkBox2);
        checkBox3 = view.findViewById(R.id.checkBox3);
        checkBox4 = view.findViewById(R.id.checkBox4);
        checkBox5 = view.findViewById(R.id.checkBox5);

        //Setting Up CheckBox
        checkBox1.setText(CommonFunctionAndClasses.skillName[1]);
        checkBox2.setText(CommonFunctionAndClasses.skillName[0]);
        checkBox3.setText(CommonFunctionAndClasses.skillName[2]);
        checkBox4.setText(CommonFunctionAndClasses.skillName[6]);
        checkBox5.setText(CommonFunctionAndClasses.skillName[4]);
        ArrayList<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.slider_image_0));
        list.add(new CarouselItem(R.drawable.slider_image_1));
        list.add(new CarouselItem(R.drawable.slider_image_2));
        carousel.setData(list);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("isUserLOGOUT", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.putBoolean("isUserLOGOUT_", false);
        editor1.apply();

        noRecordLinearLayout.setVisibility(View.GONE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        tv_customer_city.setText(city);
        toolbar_title.setText(customerCity);


        //RecycleView Implementing
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.new_pincode_layout);
                dialog.getWindow().setLayout(650, 800);
                dialog.show();

                TextInputEditText editText = dialog.findViewById(R.id.searchBoxEditText);
                TextView button = dialog.findViewById(R.id.tvContinueButtonLocation);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String varPinCode = editText.getText().toString();
                        dialog.dismiss();
                    }
                });

            }
        });
        //Call APIs
        FetchAllDataApi();


        //Setting Up check Box
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox1.isChecked()) {
                    othersCheckBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    FetchDataBySkill(1);
                } else {
                    FetchAllDataApi();
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    othersCheckBox.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    FetchDataBySkill(0);
                } else {
                    FetchAllDataApi();
                }
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    othersCheckBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                    FetchDataBySkill(2);
                } else {
                    FetchAllDataApi();
                }
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox4.isChecked()) {
                    othersCheckBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox5.setChecked(false);
                    FetchDataBySkill(6);
                } else {
                    FetchAllDataApi();
                }
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox5.isChecked()) {
                    othersCheckBox.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox1.setChecked(false);
                    FetchDataBySkill(4);
                } else {
                    FetchAllDataApi();
                }
            }
        });
        othersCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (othersCheckBox.isChecked()) {
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.search_skill_layout);
                    dialog.getWindow().setLayout(650, 800);
                    dialog.show();
                    ListView listView = dialog.findViewById(R.id.searchSkillListViewEditText);
                    EditText editText = dialog.findViewById(R.id.searchSkillEditText);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, CommonFunctionAndClasses.skillName);
                    listView.setAdapter(adapter);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            adapter.getFilter().filter(charSequence);
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            checkBox1.setChecked(false);
                            checkBox2.setChecked(false);
                            checkBox3.setChecked(false);
                            checkBox4.setChecked(false);
                            checkBox5.setChecked(false);
                            FetchDataBySkill(i);
                            dialog.dismiss();
                        }
                    });

                } else {
                    FetchAllDataApi();
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homeMenu) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (id == R.id.profileMenu) {
                    startActivity(new Intent(getContext(), MyProfile.class));
//                } else if (id == R.id.updateProfileMenu) {
//                    startActivity(new Intent(getContext(), EditProfileActivity.class));
                } else if (id == R.id.registerAsServiceProviderMenu) {
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
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        return view;
    }


    private void FetchAllDataApi() {
        startShimmerEffect();

        Call<FetchAllDataModal> call = Controller.getInstance().getApi().getServiceProviderData(CommonFunctionAndClasses.UniqueUserCode);
        call.enqueue(new Callback<FetchAllDataModal>() {
            @Override
            public void onResponse(Call<FetchAllDataModal> call, Response<FetchAllDataModal> response) {

                FetchAllDataModal obj = response.body();
                if (obj.getResult().equals("1")) {
                    stopShimmerEffect();
                    noRecordLinearLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new RecycleViewAdapter(obj.getServiceProvider(), getContext()));
                } else {
                    stopShimmerEffect();
                    recyclerView.setVisibility(View.GONE);
                    noRecordLinearLayout.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<FetchAllDataModal> call, Throwable t) {
                stopShimmerEffect();
                recyclerView.setVisibility(View.GONE);
                noRecordImageView.setImageResource(R.drawable.error_404);
                tvNoRecord.setText("");
                tvTryAgainLater.setText("");
                noRecordLinearLayout.setVisibility(View.VISIBLE);

            }
        });
    }

    private void FetchDataBySkill(int index) {
        //Getting City From Share Preference
        startShimmerEffect();


        Call<FetchAllDataModal> call = Controller.getInstance().getApi().getServiceProviderBySkill(CommonFunctionAndClasses.skillName[index], city, CommonFunctionAndClasses.UniqueUserCode);
        call.enqueue(new Callback<FetchAllDataModal>() {
            @Override
            public void onResponse(Call<FetchAllDataModal> call, Response<FetchAllDataModal> response) {
                FetchAllDataModal obj = response.body();
                loadingPlaceHolder.stopShimmerAnimation();
                loadingPlaceHolder.setVisibility(View.GONE);
                if (obj.getResult().equals("1")) {
                    stopShimmerEffect();
                    noRecordLinearLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new RecycleViewAdapter(obj.getServiceProvider(), getContext()));
                } else {
                    stopShimmerEffect();
                    recyclerView.setVisibility(View.GONE);
                    noRecordLinearLayout.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(Call<FetchAllDataModal> call, Throwable t) {
                stopShimmerEffect();
                recyclerView.setVisibility(View.GONE);
                noRecordImageView.setImageResource(R.drawable.error_404);
                tvNoRecord.setText("");
                tvTryAgainLater.setText("");
                noRecordLinearLayout.setVisibility(View.VISIBLE);


            }
        });
    }

    private void FetchDataByCity(String city) {
        startShimmerEffect();
        Call<FetchAllDataModal> call = Controller.getInstance().getApi().getServiceProviderByCity(city, CommonFunctionAndClasses.UniqueUserCode);
        call.enqueue(new Callback<FetchAllDataModal>() {
            @Override
            public void onResponse(Call<FetchAllDataModal> call, Response<FetchAllDataModal> response) {
                FetchAllDataModal obj = response.body();
                if (obj.getResult().equals("1")) {
                    stopShimmerEffect();
                    noRecordLinearLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new RecycleViewAdapter(obj.getServiceProvider(), getContext()));

                } else {
                    stopShimmerEffect();
                    recyclerView.setVisibility(View.GONE);
                    noRecordLinearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<FetchAllDataModal> call, Throwable t) {
                stopShimmerEffect();
                recyclerView.setVisibility(View.GONE);
                noRecordImageView.setImageResource(R.drawable.error_404);
                tvNoRecord.setText("");
                tvTryAgainLater.setText("");
                noRecordLinearLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    public void startShimmerEffect() {
        loadingPlaceHolder.setVisibility(View.VISIBLE);
        loadingPlaceHolder.startShimmerAnimation();

    }

    public void stopShimmerEffect() {
        loadingPlaceHolder.stopShimmerAnimation();
        loadingPlaceHolder.setVisibility(View.GONE);
    }

//    private void getLocationPermission() {
//
//        if (ContextCompat.checkSelfPermission(getContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            locationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUESTCODEFORPERMISSION
//
//            );
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        locationPermissionGranted = false;
//        if (requestCode
//                == REQUESTCODEFORPERMISSION) {// If request is cancelled, the result arrays are empty.
//            if (grantResults.length > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                locationPermissionGranted = true;
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//
//    }

}