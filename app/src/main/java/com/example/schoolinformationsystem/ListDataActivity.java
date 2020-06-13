package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listView = findViewById(R.id.listViewID5);
        databaseHelper = new DatabaseHelper(this);

        loadData();
    }

    public  void  loadData(){
         ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No data is available in database",Toast.LENGTH_LONG).show();

        }
        else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+"\t "+cursor.getString(1));
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.texVewID10,listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected value : "+selectedValue,Toast.LENGTH_LONG).show();
            }
        });

    }
}