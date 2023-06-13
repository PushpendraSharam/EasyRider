package com.example.testapis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText name, email;
    ImageView imageView;
    Button button, addImage;
    Uri filepath;
    Bitmap bitmap;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameEditText);
        email = findViewById(R.id.emailEditText);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.photoImageView);
        addImage = findViewById(R.id.addPhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String varName = name.getText().toString();
                String varEmail = email.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://servicesontap.co.in/APIs/").addConverterFactory(GsonConverterFactory.create()).build();
                Api retrofitApi = retrofit.create(Api.class);
                MultipartBody.Part part;

                File file = new File(path);
//                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//                part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
//                RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), varName);
//                RequestBody userEmail = RequestBody.create(MediaType.parse("multipart/form-data"), varEmail);
//
                RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), imageBody);
                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), varName);
                RequestBody emailBody = RequestBody.create(MediaType.parse("text/plain"), varEmail);

                Call<Modal> call = Controller.getInstance().getApi().setData(imagePart, nameBody, emailBody);
                call.enqueue(new Callback<Modal>() {
                    @Override
                    public void onResponse(Call<Modal> call, Response<Modal> response) {
                        Modal modal = response.body();
                        Toast.makeText(MainActivity.this,modal.getSuccess(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Modal> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Using"), 11);

            }
        });

    }
//    Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//    startActivityForResult(Intent.createChooser(intent, "Select Using"), 11);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {
            assert data != null;
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                //   Bitmap imageToUpload = getResizedBitmap(bitmap, 500);
                // filepath = getImageUri(Form.this, imageToUpload);
                Uri uri = data.getData();
                Context context = MainActivity.this;
                path = RealPathUtil.getRealPath(context, uri);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                imageView.setImageBitmap(bitmap);
                //  isImageGet = true;


            } catch (Exception e) {
                Toast.makeText(this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}