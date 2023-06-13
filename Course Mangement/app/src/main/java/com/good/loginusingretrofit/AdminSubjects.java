package com.good.loginusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.models.AdminSubjectModalClass;
import com.good.loginusingretrofit.recycleAdapter.AdminSubjectAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSubjects extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swiper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subjects);
        toolbar=findViewById(R.id.adminSubjectsToolbar);
        setSupportActionBar(toolbar);
        swiper=findViewById(R.id.adminSubjectsSwiper);
        getSupportActionBar().setTitle("Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String bid= getIntent().getStringExtra("AdminBid");
        recyclerView=findViewById(R.id.adminSubjectsRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Call<AdminSubjectModalClass> call = Controler.getInstance().getApi().getAdminSubjects(bid);
        call.enqueue(new Callback<AdminSubjectModalClass>() {
            @Override
            public void onResponse(Call<AdminSubjectModalClass> call, Response<AdminSubjectModalClass> response) {
                AdminSubjectModalClass obj= response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(AdminSubjects.this));
                recyclerView.setAdapter(new AdminSubjectAdapter(getApplicationContext(),obj.getData()));
            }

            @Override
            public void onFailure(Call<AdminSubjectModalClass> call, Throwable t) {
                Toast.makeText(AdminSubjects.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<AdminSubjectModalClass> call = Controler.getInstance().getApi().getAdminSubjects(bid);
                call.enqueue(new Callback<AdminSubjectModalClass>() {
                    @Override
                    public void onResponse(Call<AdminSubjectModalClass> call, Response<AdminSubjectModalClass> response) {
                        AdminSubjectModalClass obj= response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(AdminSubjects.this));
                        recyclerView.setAdapter(new AdminSubjectAdapter(getApplicationContext(),obj.getData()));
                    }

                    @Override
                    public void onFailure(Call<AdminSubjectModalClass> call, Throwable t) {
                        Toast.makeText(AdminSubjects.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                swiper.setRefreshing(false);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}