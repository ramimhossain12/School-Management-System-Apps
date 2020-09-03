package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentRecordActivity extends AppCompatActivity   {

    private EditText name,email,pass;
    private Button save,fetch,remove,clear;
    private TextView textView;

    String Name = "Name Key"; String Email = "Email Key"; String Result = "Result Key";

    SharedPreferences sp;
    SharedPreferences.Editor ed;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);

        name = findViewById(R.id.nameID);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.passID);
        save= findViewById(R.id.btnID);
        fetch = findViewById(R.id.fetchID);
        remove = findViewById(R.id.removeID);
        clear = findViewById(R.id.clearID);
        textView = findViewById(R.id.ttID);



        sp = getSharedPreferences("Sasasushiq", Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed = sp.edit();
                ed.putString(Name, name.getText().toString());
                ed.putString(Email, email.getText().toString());
                ed.putString(Result, pass.getText().toString());
                ed.apply();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.contains(Name))
                    name.setText(sp.getString(Name, ""));
                if (sp.contains(Email))
                    email.setText(sp.getString(Email, ""));
                if (sp.contains(Result))
                    pass.setText(sp.getString(Result ,""));

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                pass.setText("");
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.remove(Name);
                ed.remove(Email);
                ed.remove(Result);
                ed.apply();
            }
        });

    }


}
