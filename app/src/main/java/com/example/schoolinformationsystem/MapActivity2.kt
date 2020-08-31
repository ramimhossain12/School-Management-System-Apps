package com.example.schoolinformationsystem

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MapActivity2 : AppCompatActivity() {
    lateinit var mGoogleMapBtn :Button
    val  uri = "https://www.google.com.bd/maps/@23.7806365,90.4193257,12z"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map2)

        mGoogleMapBtn = findViewById(R.id.mapBtn)

        mGoogleMapBtn.setOnClickListener{
            val  uriMap = Uri.parse(uri)
            val intent = Intent(Intent.ACTION_VIEW,uriMap)
            intent.setPackage("com.google.android.apps.maps")
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }

        }
    }
}