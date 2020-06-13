package com.example.schoolinformationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static  final   String DATABASE_NAME = "student.db";
    private  static  final   String TABLE_NAME = "student_details";
    private  static  final   String ID = "id";
    private  static  final   String NAME = "Name";
    private  static  final   int   VERSION_NUMBER = 1 ;
    private  static  final   String   CREATE_TABLE  = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY,"+NAME+" VARCHAR(30)) ;" ;

     private  Context context;

    public DatabaseHelper( Context context  ) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"onCreate is called : ",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_LONG).show();
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }catch (Exception e){

            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        }

    }

    public  long  saveData(String id,String name)
    {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(ID,id);

        long rowNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowNumber;

    }

    public Cursor showAllData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Boolean updateData(String id,String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[] {id});
        return  true;
    }

    public  int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
          int value = sqLiteDatabase.delete(TABLE_NAME,ID+" = ?", new String[] {id});
          return  value;
    }


}
