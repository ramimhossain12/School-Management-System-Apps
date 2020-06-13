package com.example.schoolinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);


        webView= findViewById(R.id.webID);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://education.findlaw.com/student-conduct-and-discipline/school-discipline.html");


        ImageSlider imageSlider=findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();

        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/12/15/06/42/kids-1093758_960_720.jpg","Student "));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/07/19/10/00/still-life-851328_960_720.jpg","Study "));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/06/29/08/50/pencil-1486278_960_720.jpg","Pen"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/06/09/02/42/crayons-1445053_960_720.jpg","Drawing"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2015/07/31/11/45/library-869061_960_720.jpg","Libary"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2016/06/01/06/26/open-book-1428428_960_720.jpg","Book"));

        imageSlider.setImageList(slideModels,true);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }

    }
}
