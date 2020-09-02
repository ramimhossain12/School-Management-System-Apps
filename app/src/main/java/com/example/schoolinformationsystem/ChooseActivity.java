package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {
             private  RadioButton optaion1,optaion2;
             private  Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        optaion1 = findViewById(R.id.option1);
        optaion2 = findViewById(R.id.option2);
        button = findViewById(R.id.submitID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (optaion1.isChecked()){

                     Intent i = new Intent(ChooseActivity.this,TeacherLoginActivity.class);
                     startActivity(i);
                 }
                 else if (optaion2.isChecked()){

                     Intent io = new Intent(ChooseActivity.this,MainActivity.class);
                     startActivity(io);

                 }
            }
        });




    }
}