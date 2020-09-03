package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();



        CircleMenu menu = findViewById(R.id.circleMenu);
        menu.setMainMenu(Color.parseColor("#212121"), R.drawable.ic_home_white_24dp, R.drawable.ic_home_white_24dp);
        menu.addSubMenu(Color.parseColor("#7986cb"), R.drawable.qrcode);
        menu.addSubMenu(Color.parseColor("#ededed"), R.drawable.school);
        menu.addSubMenu(Color.parseColor("#FBF6D9"), R.drawable.like);
        menu.addSubMenu(Color.parseColor("#8c9eff"), R.drawable.evaluation);
        menu.addSubMenu(Color.parseColor("#FBF6D9"), R.drawable.clender);

        Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.myanimation);
        menu.startAnimation(animation);



        menu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                if (i == 0) {
                    Toast.makeText(HomeActivity.this, "QR Code", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(HomeActivity.this, BarCodeScanner.class);
                    startActivity(in);

                } else if (i == 1) {
                    Toast.makeText(HomeActivity.this, "Student", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(HomeActivity.this, StudentActivity.class);
                    startActivity(in);
                } else if (i == 2) {
                    Toast.makeText(HomeActivity.this, "About", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(HomeActivity.this, AboutActivity.class);
                    startActivity(in);
                } else if (i == 3) {
                    Toast.makeText(HomeActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(HomeActivity.this, FeedbackActivity.class);
                    startActivity(in);
                } else if (i == 4) {
                    Toast.makeText(HomeActivity.this, "Date", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(HomeActivity.this, DatePickerActivity.class);
                    startActivity(in);


                }

            }
        });
    }



}
