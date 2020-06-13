package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AttendenceActivity extends AppCompatActivity implements View.OnClickListener {

    private  DatabaseHelper databaseHelper;
    private EditText nameEdit,idEdit;
    private Button saveButton,showButton,updateButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        databaseHelper = new DatabaseHelper(this);
      SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

      nameEdit = findViewById(R.id.nameEitText4ID);
      idEdit = findViewById(R.id.idEitText4ID);

      saveButton = findViewById(R.id.saveButton4ID);
      showButton = findViewById(R.id.showButton4ID);
      updateButton = findViewById(R.id.updateButton4ID);
      deleteButton = findViewById(R.id.deleteButton4ID);


        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String id = idEdit.getText().toString();
        String name = nameEdit.getText().toString();

        if (v.getId()==R.id.saveButton4ID){

            if (id.equals("") && name.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all data",Toast.LENGTH_LONG).show();

            }


            else {
                long rowNumber = databaseHelper.saveData(id,name);
                if (rowNumber> -1){
                    Toast.makeText(getApplicationContext(),"Data is inserted successful",Toast.LENGTH_LONG).show();
                    idEdit.setText("");
                    nameEdit.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data is not inserted successful",Toast.LENGTH_LONG).show();

                }
            }

        }
        else if (v.getId()==R.id.showButton4ID){

            Intent i = new Intent(AttendenceActivity.this,ListDataActivity.class);
            startActivity(i);

        }else if (v.getId()==R.id.updateButton4ID){

     Boolean isUpdated = databaseHelper.updateData(id,name);
     if (isUpdated == true){

         Toast.makeText(getApplicationContext(),"data is updated",Toast.LENGTH_LONG).show();

     }else {
         Toast.makeText(getApplicationContext(),"data is not updated",Toast.LENGTH_LONG).show();
     }

        }
        else if (v.getId()==R.id.deleteButton4ID){

            int value = databaseHelper.deleteData(id);
            if (value<0){
                Toast.makeText(getApplicationContext(),"data is not deleted",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"data is  deleted",Toast.LENGTH_LONG).show();
            }

        }
    }
}