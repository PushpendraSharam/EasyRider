package com.good.loginusingretrofit;

import static android.Manifest.permission_group.STORAGE;
import static com.good.loginusingretrofit.Controler.Controler.getInstance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.models.ProfileModalClass;
import com.good.loginusingretrofit.models.UpdateModalClass;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText name, number,city,email;
    Button update;
    String path=null;
    String url;
    ImageView edit, profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        toolbar = findViewById(R.id.app_toolbarEditProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");
        name = findViewById(R.id.updateName);
        number = findViewById(R.id.updateNumber);
        city = findViewById(R.id.updateCity);
        update = findViewById(R.id.updateData);
        edit = findViewById(R.id.editByCamera);
        email=findViewById(R.id.editMail);
        profileImage = findViewById(R.id.profileImageEdit);
        Call<ProfileModalClass> call=Controler.getInstance().getApi().getProfile(gettid());
        call.enqueue(new Callback<ProfileModalClass>() {
            @Override
            public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                ProfileModalClass data= response.body();
                if(data.getSuccess().equals("false")) {
                    Toast.makeText(EditProfile.this, data.getMessage(), Toast.LENGTH_SHORT).show();

                }
                else
                {
                    name.setText(data.getData().getT_name());
                    email.setText(data.getData().getT_email());
                    number.setText(data.getData().getT_number());
                    city.setText(data.getData().getT_location());
                    url = data.getData().getImage();
                    Picasso.get().load(url).into(profileImage);
                }
            }

            @Override
            public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                Toast.makeText(EditProfile.this, "Fail to Load data", Toast.LENGTH_SHORT).show();
            }
        });


        String tid = gettid();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 10);
                } else {
                    ActivityCompat.requestPermissions(EditProfile.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = name.getText().toString();
                String tnumber = number.getText().toString();
                String tlocation = city.getText().toString();
                if (tlocation.isEmpty() || tname.isEmpty() || tnumber.isEmpty()) {
                    Toast.makeText(EditProfile.this, "Please fill All fields", Toast.LENGTH_SHORT).show();
                }
                else{

                    updateProfileFunction(tid,tlocation,tnumber,tname);
                    Toast.makeText(EditProfile.this, "Update Successful", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(EditProfile.this,MyProfileActivity.class));
                finish();
            }
        });
    }


    String gettid() {
        SharedPreferences sp=getSharedPreferences("LoginPage",MODE_PRIVATE);
        String id=sp.getString("ID","1");
        return  id;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri=data.getData();
            Context context=EditProfile.this;
            path= RealPathUtil.getRealPath(context,uri);
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            profileImage.setImageBitmap(bitmap);
            }
    }



    void updateProfileFunction(String id,String location ,String number,String name )
        {
            MultipartBody.Part part;
            if(path!=null) {
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                part = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
            }
            else
            {
                part=null;
            }
                RequestBody tid = RequestBody.create(MediaType.parse("multipart/form-data"), id);
                RequestBody tlocation = RequestBody.create(MediaType.parse("multipart/form-data"), location);
                RequestBody tnumber = RequestBody.create(MediaType.parse("multipart/form-data"), number);
                RequestBody tname = RequestBody.create(MediaType.parse("multipart/form-data"), name);

            Call<UpdateModalClass> call = Controler.getInstance().getApi().setUpdate(tid,tlocation,tnumber,tname,part);
            call.enqueue(new Callback<UpdateModalClass>() {
                @Override
                public void onResponse(Call<UpdateModalClass> call, Response<UpdateModalClass> response) {
                    UpdateModalClass data = response.body();


                }

                @Override
                public void onFailure(Call<UpdateModalClass> call, Throwable t) {
                    Toast.makeText(EditProfile.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                }
            });

        }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(EditProfile.this,MyProfileActivity.class));
       finish();
        return super.onSupportNavigateUp();

    }
}
