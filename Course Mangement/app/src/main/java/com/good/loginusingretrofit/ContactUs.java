package com.good.loginusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {
EditText name,email,issue;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar=findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");
        name=findViewById(R.id.contactName);
        email=findViewById(R.id.contactEmail);
        issue=findViewById(R.id.contactIssue);
        submit=findViewById(R.id.contactSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||issue.getText().toString().isEmpty())
                {
                    Toast.makeText(ContactUs.this, "All fields required", Toast.LENGTH_SHORT).show(); 
                }
                else {
                    name.setText("");
                    email.setText("");
                    issue.setText("");
                    Toast.makeText(ContactUs.this, "Submit Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}