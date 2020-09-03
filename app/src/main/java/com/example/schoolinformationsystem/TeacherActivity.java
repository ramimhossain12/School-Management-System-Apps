package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class TeacherActivity extends AppCompatActivity  implements View.OnClickListener{


    private CardView home,record,atten,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        home= findViewById(R.id.cardHomeID);
        record= findViewById(R.id.cardrecordID);
        add = findViewById(R.id.cardstudenaddID);
        atten= findViewById(R.id.cardattendenceID);

        home.setOnClickListener(this);
        record.setOnClickListener(this);
        add.setOnClickListener(this);
        atten.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      switch (v.getId()){

          case R.id.cardHomeID:
              startActivity(new Intent(this, TeacherLoginActivity.class));

              StyleableToast.makeText(this,"Home Button Is Click !",R.style.exampleToast).show();

              break;

          case R.id.cardrecordID:
              startActivity(new Intent(this, StudentRecordActivity.class));

              StyleableToast.makeText(this,"Record Button Is Click !",R.style.exampleToast).show();

              break;

          case R.id.cardattendenceID:
              startActivity(new Intent(this, AttendenceActivity.class));

              StyleableToast.makeText(this,"Attendence Button Is Click !",R.style.exampleToast).show();

              break;

          case R.id.cardstudenaddID:
              startActivity(new Intent(this, AddStudentActivity.class));

              StyleableToast.makeText(this,"Add Button Is Click !",R.style.exampleToast).show();

              break;




      }
    }
}
