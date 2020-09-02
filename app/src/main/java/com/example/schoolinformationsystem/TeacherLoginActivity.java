package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.security.auth.login.LoginException;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText Name, Password;
    private Button login;
    private TextView info;
    private  int counter =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnLogin);
        info = findViewById(R.id.tvinfo);

        info.setText("No of attempts remaining : 5");

   login.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           validate(Name.getText().toString(),Password.getText().toString());
       }
   });
    }

    private  void  validate(String userName, String userPassword){
        if ((userName.equals("admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(TeacherLoginActivity.this,TeacherActivity.class);
            startActivity(intent);
        }else {
                     counter--;
                     info.setText("No of attempts remaining:" + String.valueOf(counter));
                     if (counter == 0){
                       login.setEnabled(false);
                     }
        }
    }
}