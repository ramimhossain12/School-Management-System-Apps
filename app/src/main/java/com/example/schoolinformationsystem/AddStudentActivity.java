package com.example.schoolinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private  EditText  eidt;
    private Spinner spinner;
    private Button bt;
    ListView listView;
    List<Artist> artistList;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artist");

        eidt = findViewById(R.id.editTextname24ID);
        spinner = findViewById(R.id.spinner24ID);
        bt= findViewById(R.id.button24ID);
        listView= findViewById(R.id.listviewArtistID);
        artistList = new ArrayList<>();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              addArtist();
            }
        });



     listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
         @Override
         public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

             Artist artist = artistList.get(position);
             showUpdateDialog(artist.getArtistID(),artist.getArtistName());

             return false;
         }
     });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                artistList.clear();

                for (DataSnapshot artistSanapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSanapshot.getValue(Artist.class);

                    artistList.add(artist);
                }

                 ArtistList adapter = new ArtistList(AddStudentActivity.this,artistList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//update dialog
    private  void showUpdateDialog(final String artistID, String artistName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final  View dialogView = inflater.inflate(R.layout.update_dialog,null);

        dialogBuilder.setView(dialogView);
        final  Spinner spinnerGenres = dialogView.findViewById(R.id.spinnerGenres);
        final EditText editTextName = dialogView.findViewById(R.id.editTextName);
        final Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdateID);
        final  Button buttonDelete = dialogView.findViewById(R.id.buttonDeleteID);


        dialogBuilder.setTitle("Updating Artist "+artistName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenres.getSelectedItem().toString();

                if (TextUtils.isEmpty(name)){
                    editTextName.setError("Name required");
                    return;
                }
                updateArtist(artistID,name,genre);
                alertDialog.dismiss();
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(artistID);
            }
        });



    }

    private void deleteArtist(String artistID) {

        DatabaseReference drArtist = FirebaseDatabase.getInstance().getReference("artist").child(artistID);

        drArtist.removeValue();

        Toast.makeText(this,"Data is Deleted",Toast.LENGTH_LONG).show();
    }


    private  boolean updateArtist(String id, String name ,String genre){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("artist").child(id);
        Artist artist = new Artist(id,name,genre);
        databaseReference.setValue(artist);
        Toast.makeText(this,"Update Successfully",Toast.LENGTH_LONG).show();
        return  true;

    }


    private  void addArtist(){

        String name = eidt.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
                String id = databaseArtists.push().getKey();
                Artist artist = new Artist(id,name,genre);
                databaseArtists.child(id).setValue(artist);
                Toast.makeText(this,"Artist added",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"You should a name",Toast.LENGTH_LONG).show();
        }
    }
}