package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotebookActivity extends AppCompatActivity implements View.OnClickListener {

    //    Button
    private EditText editText;
    private Button savebutton;
    private Button exitButton;
    private android.app.AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);


//find the button
        editText = findViewById(R.id.editTextID);
        savebutton = findViewById(R.id.saveButtonID);
        exitButton = findViewById(R.id.buttonID);
        exitButton.setOnClickListener(this);
    }

 



    //menu item find

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }






    public  void writeToFile(String text){


        try {
            FileOutputStream fileOutputStream = openFileOutput("Note.text", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(),"  datais saved ",Toast.LENGTH_SHORT);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    public  void readfFromFile(){

        try {
            FileInputStream fileInputStream = openFileInput("Note.text");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line ;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine())!=null){

                stringBuffer.append(line+"\n");
            }

            editText.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //back button click
    @Override
    public void onBackPressed() {

        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want Exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NotebookActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


    // alertdilog

    @Override
    public void onClick(View v) {

        alertDialogBuilder = new android.app.AlertDialog.Builder(NotebookActivity.this);
        alertDialogBuilder.setTitle(R.string.title_Text);

        alertDialogBuilder.setMessage(R.string.message_Text);



        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });


        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(NotebookActivity.this,"You have clicked on cancel button",Toast.LENGTH_SHORT).show();

            }
        });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}