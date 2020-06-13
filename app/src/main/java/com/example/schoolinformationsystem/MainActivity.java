package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button button;
    private ImageView imageView;




    String[] selet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel =
                    new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successfull";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }



                    }
                });






        textView = findViewById(R.id.login21ID);
        button= findViewById(R.id.join_us21ID);
        imageView=findViewById(R.id.imageViewID);




        button.setOnClickListener(this);
        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.myanimation);
        button.startAnimation(animation);
        imageView.startAnimation(animation);
        textView.startAnimation(animation);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join_us21ID:
                startActivity(new Intent( MainActivity.this,SignUpACtivity.class));
                break;
            case R.id.login21ID:
                startActivity(new Intent( MainActivity.this,SignInActivity.class));
                break;
        }

    }
}
