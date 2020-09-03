package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {


    private Button sendButton, clearButton;
    private EditText nameeditText, massegeEditText;

    private RatingBar ratingBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        sendButton = findViewById(R.id.sendButtonID);
        clearButton = findViewById(R.id.clearButtonID);


        nameeditText = findViewById(R.id.nameEditTextID);
        massegeEditText = findViewById(R.id.massegeEditTextID);

        ratingBar = findViewById(R.id.rattingbarID);
        textView = findViewById(R.id.textViewIDI);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView.setText("Value :" + ratingBar.getProgress());

            }
        });

        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {
            String name = nameeditText.getText().toString();
            String massege = massegeEditText.getText().toString();
            String text =  textView.getText().toString();
            if (v.getId() == R.id.sendButtonID) {

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/email");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ramimhossain6242@gmail.com"});

                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT, "Name :" + name + "\n Message :" + massege);
                intent.putExtra(Intent.EXTRA_TEXT, "Rate :" + text + "\n Message :" + massege);
                startActivity(Intent.createChooser(intent, "Feedback with"));


            } else if (v.getId() == R.id.clearButtonID) {
                nameeditText.setText("");
                massegeEditText.setText("");
            }


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Exception :" + e, Toast.LENGTH_SHORT).show();

        }

    }


    }