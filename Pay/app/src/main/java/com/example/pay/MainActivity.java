package com.example.pay;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    TextView tv_date;
    String[] moon = {"Morning", "Evening"};
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.appToolbar);
        tv_date = findViewById(R.id.tv_date);
        spinner = findViewById(R.id.spinner);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        //Setting Up toolbar Menu
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Getting User Current Date
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(currentDate);
        tv_date.setText(dateString);

        //Setting Drop Down Menu
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinder_layout, moon);
        spinner.setAdapter(adapter);


        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of the DatePickerDialog class and set the OnDateSetListener
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Get the selected date values and use them as needed in your app
                                Log.d("MainActivity", "Selected date: " + year + "/" + (month + 1) + "/" + dayOfMonth);
                                tv_date.setText(dayOfMonth + "/" + month + 1 + "/" + year);
                            }
                        },
                        year, month, day);

                // Show the date picker dialog

                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}