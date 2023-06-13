package com.good.loginusingretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.models.AdminBatchesModalClass;
import com.good.loginusingretrofit.models.ProfileModalClass;
import com.good.loginusingretrofit.recycleAdapter.AdminAdapter;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView headerTitle;
    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayout falseConnection;
    Button retry;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        toolbar = findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Course Management");
        recyclerView = findViewById(R.id.adminRecycleView);
        drawerLayout = findViewById(R.id.admindrawerLayout);
        navigationView = findViewById(R.id.adminnavigationDrawer);
        swipeRefreshLayout = findViewById(R.id.adminrecycleViewRefresh);
        falseConnection = findViewById(R.id.falseConnectionAdmin);
        retry = findViewById(R.id.retryButtonAdmin);
        progressBar = findViewById(R.id.progressBarNoInternetAdmin);
      
//        toggle = new ActionBarDrawerToggle(Admin.this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            falseConnection.setVisibility(View.GONE);
            toggle = new ActionBarDrawerToggle(Admin.this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

        } else {
            toolbar.setNavigationIcon(null);
            falseConnection.setVisibility(View.VISIBLE);
        }

        View view = navigationView.inflateHeaderView(R.layout.haderlayout);
        headerTitle = view.findViewById(R.id.header_title);
        TextView headerEmail = view.findViewById(R.id.header_email);
        headerEmail.setText(getTeacherEmail());
        Call<ProfileModalClass> call1 = Controler.getInstance().getApi().getProfile(getTeacherID());
        call1.enqueue(new Callback<ProfileModalClass>() {
            @Override
            public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                ProfileModalClass data = response.body();
                headerTitle.setText(data.getData().getT_name());
                headerEmail.setText(data.getData().getT_email());
                String url = data.getData().getImage();
                ImageView headerImage = findViewById(R.id.headerImage);
                Picasso.get().load(url).into(headerImage);

            }

            @Override
            public void onFailure(Call<ProfileModalClass> call, Throwable t) {

            }
        });
        Call<AdminBatchesModalClass> call = Controler.getInstance().getApi().getAdminBatches();
        call.enqueue(new Callback<AdminBatchesModalClass>() {
            @Override
            public void onResponse(Call<AdminBatchesModalClass> call, Response<AdminBatchesModalClass> response) {
                AdminBatchesModalClass obj = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(Admin.this));
                AdminAdapter adapter = new AdminAdapter(obj.getData(), getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AdminBatchesModalClass> call, Throwable t) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                toggle = new ActionBarDrawerToggle(Admin.this, drawerLayout, toolbar, R.string.open, R.string.close);
                drawerLayout.addDrawerListener(toggle);
                toggle.syncState();
                Call<AdminBatchesModalClass> call = Controler.getInstance().getApi().getAdminBatches();
                call.enqueue(new Callback<AdminBatchesModalClass>() {
                    @Override
                    public void onResponse(Call<AdminBatchesModalClass> call, Response<AdminBatchesModalClass> response) {
                        AdminBatchesModalClass obj = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(Admin.this));
                        AdminAdapter adapter = new AdminAdapter(obj.getData(), getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<AdminBatchesModalClass> call, Throwable t) {
                    }
                });
                Call<ProfileModalClass> call1 = Controler.getInstance().getApi().getProfile(getTeacherID());
                call1.enqueue(new Callback<ProfileModalClass>() {
                    @Override
                    public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                        ProfileModalClass data = response.body();
                        headerTitle.setText(data.getData().getT_name());
                        headerEmail.setText(data.getData().getT_email());
                        String url = data.getData().getImage();
                        ImageView headerImage = findViewById(R.id.headerImage);
                        Picasso.get().load(url).into(headerImage);

                    }

                    @Override
                    public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                        Toast.makeText(Admin.this, "Fail to Load data", Toast.LENGTH_SHORT).show();
                    }
                });
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    falseConnection.setVisibility(View.GONE);

                } else {
                    toolbar.setNavigationIcon(null);
                    falseConnection.setVisibility(View.VISIBLE);
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homeDrawerAdmin) {
                    Toast.makeText(Admin.this, id, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.logOutAdmin) {
                    new AlertDialog.Builder(Admin.this)
                            .setTitle("Really Exit?")
                            .setMessage("Are you sure you want to Logout?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    SharedPreferences sp = getSharedPreferences("LoginPage", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("Type", "random");
                                    editor.putString("ID", "");
                                    editor.apply();
                                    Intent intent = new Intent(Admin.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }).create().show();

                } else if (id == R.id.myProfileAdmin) {
                    startActivity(new Intent(Admin.this, MyProfileActivity.class));
                    finish();
                } else if (id == R.id.contactUsAdmin) {
                    startActivity(new Intent(Admin.this, ContactUs.class));
                } else if (id == R.id.navShareAdmin) {
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, "Download the Application to manage your course\n\n https://play.google.com/store/apps/details?id=" + getPackageName());
                    startActivity(Intent.createChooser(i, "Share Using"));
                }
                else if(id==R.id.aboutUsAdmin)
                {
                    Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    falseConnection.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    toggle = new ActionBarDrawerToggle(Admin.this, drawerLayout, toolbar, R.string.open, R.string.close);
                    drawerLayout.addDrawerListener(toggle);
                    toggle.syncState();
                    Call<AdminBatchesModalClass> call = Controler.getInstance().getApi().getAdminBatches();
                    call.enqueue(new Callback<AdminBatchesModalClass>() {
                        @Override
                        public void onResponse(Call<AdminBatchesModalClass> call, Response<AdminBatchesModalClass> response) {
                            AdminBatchesModalClass obj = response.body();
                            recyclerView.setLayoutManager(new LinearLayoutManager(Admin.this));
                            AdminAdapter adapter = new AdminAdapter(obj.getData(), getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure(Call<AdminBatchesModalClass> call, Throwable t) {
                            Toast.makeText(Admin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    Call<ProfileModalClass> call1 = Controler.getInstance().getApi().getProfile(getTeacherID());

                    call1.enqueue(new Callback<ProfileModalClass>() {
                        @Override
                        public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                            ProfileModalClass data = response.body();
                            headerTitle.setText(data.getData().getT_name());
                            headerEmail.setText(data.getData().getT_email());
                            String url = data.getData().getImage();
                            ImageView headerImage = findViewById(R.id.headerImage);
                            Picasso.get().load(url).into(headerImage);

                        }

                        @Override
                        public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                            Toast.makeText(Admin.this, "Fail to Load data", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    falseConnection.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    }, 2700);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }
    }

    String getTeacherEmail() {
        SharedPreferences sp = getSharedPreferences("LoginPage", MODE_PRIVATE);
        String name = sp.getString("Email", "User");
        return name;
    }

    String getTeacherID() {
        SharedPreferences sp = getSharedPreferences("LoginPage", MODE_PRIVATE);
        String id = sp.getString("ID", "1");
        return id;
    }

}