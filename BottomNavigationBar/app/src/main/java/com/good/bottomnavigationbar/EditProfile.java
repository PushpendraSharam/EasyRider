package com.good.bottomnavigationbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.good.bottomnavigationbar.ModalClass.UpdateModalClass;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    Toolbar toolbar;
    Button submit;
    ImageView image,add;
    TextInputEditText name,mail,number,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar = findViewById(R.id.app_toolbarEditProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        name = findViewById(R.id.editName);
        submit = findViewById(R.id.submitProfileButton);
        add=findViewById(R.id.editByCamera);
         image=findViewById(R.id.profileImage);
         mail=findViewById(R.id.editMail);
         number=findViewById(R.id.editNumber);
         city=findViewById(R.id.editCity);

        SharedPreferences sp=getSharedPreferences("Teacher Detail",MODE_PRIVATE);
        name.setText(sp.getString("T_name","No Data"));
        mail.setText(sp.getString("T_email","No Data"));
        number.setText(sp.getString("T_number","No Data"));
        city.setText(sp.getString("T_city","No Data"));

       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname=name.getText().toString();
                String tnumber=number.getText().toString();
                String tlocation=city.getText().toString();


                if(tlocation.isEmpty()||tname.isEmpty()||tnumber.isEmpty())
                {
                    Toast.makeText(EditProfile.this, "Please fill All fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    retrofit2.Call<UpdateModalClass> call= Controler.getInstance().getApi().setUpdate("1",tlocation,tnumber,tname);
                    call.enqueue(new Callback<UpdateModalClass>() {
                        @Override
                        public void onResponse(retrofit2.Call<UpdateModalClass> call, Response<UpdateModalClass> response) {
                            UpdateModalClass data= response.body();
                            SharedPreferences sp=getSharedPreferences("Teacher Detail",MODE_PRIVATE);
                            SharedPreferences.Editor edit=sp.edit();
                            edit.putString("T_name",data.getData()[0].getT_name());
                            edit.putString("T_email",data.getData()[0].getT_email());
                            edit.putString("T_number",data.getData()[0].getT_number());
                            edit.putString("T_city",data.getData()[0].getT_location());
                            edit.commit();
                            getSupportFragmentManager().beginTransaction().replace(R.id.editProfileLayout,new Search()).commit();
                            finish();

                        }

                        @Override
                        public void onFailure(Call<UpdateModalClass> call, Throwable t) {
                            Toast.makeText(EditProfile.this, "Falied to Update", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text = name.getText().toString();
//                Toast.makeText(EditProfile.this, text, Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}