package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    //menu item find

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (item.getItemId()==R.id.shareID){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/type");
            String subject = "Note_Book app";
            String body ="This app  is very useful .\n com.example.schoolinformationsystem";
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent,"share with"));


        }else if (item.getItemId()==R.id.rateappId){
           // Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
            //startActivity(intent);




        }else if (id == R.id.moreappId){
           // Intent intent = new Intent(this,about.class);
            //startActivity(intent);
            return true;
        }else if (id==R.id.dateID){
            Intent intent = new Intent(this,DatePickerActivity.class);
            startActivity(intent);
        }
        else if (id ==R.id.aboutID){
            Intent intent = new Intent(this,AboutActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

}