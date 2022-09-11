package com.master.design.rashnanthi.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.R;

public class ImageActicity extends AppCompatActivity {
    private ImageView mImg;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_acticity);
        String url = getIntent().getStringExtra("url");
        mImg = findViewById(R.id.image_id);
        webView = findViewById(R.id.webView);
        if (mImg != null) {
            if(url.contains("mp4")){
                mImg.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(url);


            }else{
                mImg.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                Glide.with(ImageActicity.this).load(url).into(mImg);
            }


        }
    }
}
