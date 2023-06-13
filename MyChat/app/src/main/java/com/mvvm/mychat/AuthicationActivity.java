package com.mvvm.mychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mvvm.mychat.databinding.ActivityAuthicationBinding;

public class AuthicationActivity extends AppCompatActivity {
    ActivityAuthicationBinding binding;
    String name, email, password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                name = binding.name.getText().toString();
                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(AuthicationActivity.this, "Field Can't be empty", Toast.LENGTH_SHORT).show();

                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(AuthicationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 7) {
                    Toast.makeText(AuthicationActivity.this, "Password should contain more than 7 characters", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                            if (isNewUser) {
                                Toast.makeText(AuthicationActivity.this, "User Not Exits\n Please SignIn", Toast.LENGTH_SHORT).show();
                            } else {
                                login();
                            }

                        }
                    });

                }
            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.name.getText().toString();
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(AuthicationActivity.this, "Field Can't be empty", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(AuthicationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 7) {
                    Toast.makeText(AuthicationActivity.this, "Password should contain more than 7 characters", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                            if (isNewUser) {
                                SignUp();
                            } else {
                                Toast.makeText(AuthicationActivity.this, "User Already Exist\nPlease Login", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(AuthicationActivity.this, MainActivity.class));
            finishAffinity();
        }
    }

    private void login() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(AuthicationActivity.this, MainActivity.class));
                finishAffinity();
            }
        });
    }

    private void SignUp() {


        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.trim(), password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(AuthicationActivity.this, "Register", Toast.LENGTH_SHORT).show();

                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        ModalClass modalClass = new ModalClass(FirebaseAuth.getInstance().getUid(), name, email, password);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(modalClass);
                        startActivity(new Intent(AuthicationActivity.this, MainActivity.class));
                        finishAffinity();

                    }
                });
    }
}