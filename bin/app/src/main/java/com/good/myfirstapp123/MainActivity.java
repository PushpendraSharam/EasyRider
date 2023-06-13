package com.good.myfirstapp123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText editText,editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button1);
        textView=findViewById(R.id.textView1);
        editText=findViewById(R.id.editText1);
        editText1=findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(editText.getText().toString());
                int num1=Integer.parseInt(editText1.getText().toString());
                int sum=num+num1;
                String str=Integer.toString(sum);
                textView.setText(str);
            }
        });
    }
}