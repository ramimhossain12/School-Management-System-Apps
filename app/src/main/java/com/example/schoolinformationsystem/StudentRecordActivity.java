package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentRecordActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText name,email,pass;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_record);

        name = findViewById(R.id.nameID);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.passID);
        button= findViewById(R.id.btnID);
        textView = findViewById(R.id.ttID);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String pass1 = pass.getText().toString();

        if (v.getId()==R.id.btnID){

            textView.setText("Name: "+name1+"\nEmail: "+email1+"\nResult: "+pass1);
        }
    }
}
