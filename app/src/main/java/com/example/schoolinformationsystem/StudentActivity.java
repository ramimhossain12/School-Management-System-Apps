package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {


    private CardView home, note,edu,quiz,news,dc , map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        home= findViewById(R.id.studenthomeID);
        note = findViewById(R.id.studentNoteID);
        edu=findViewById(R.id.studentedusiteID);
        quiz=findViewById(R.id.studentQuizID);
        news=findViewById(R.id.stunewspaerID);
        dc = findViewById(R.id.schoolDCID);
        map= findViewById(R.id.googleMapID);


        home.setOnClickListener(this);
        note.setOnClickListener(this);
        edu.setOnClickListener(this);
        quiz.setOnClickListener(this);
        dc.setOnClickListener(this);
        news.setOnClickListener(this);
        map.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.studenthomeID:
                startActivity(new Intent(StudentActivity.this,HomeActivity.class));
                break;

            case R.id.studentNoteID:
                startActivity(new Intent(StudentActivity.this,NotebookActivity.class));
                break;

            case R.id.studentedusiteID:
                startActivity(new Intent(StudentActivity.this,EducationalActivity.class));
                break;

            case R.id.studentQuizID:
                startActivity(new Intent(StudentActivity.this,QuizActivity.class));
                break;

            case R.id.stunewspaerID:
                startActivity(new Intent(StudentActivity.this,NewsActivity.class));
                break;

            case R.id.schoolDCID:
                startActivity(new Intent(StudentActivity.this,SchoolActivity.class));
                break;
            case R.id.googleMapID:
                startActivity(new Intent(StudentActivity.this,MapsActivity.class));
                break;
        }

    }
}
