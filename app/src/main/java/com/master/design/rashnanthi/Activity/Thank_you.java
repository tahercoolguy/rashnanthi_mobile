package com.master.design.rashnanthi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.master.design.rashnanthi.R;

public class Thank_you extends AppCompatActivity {

    ImageView thank_you_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        thank_you_back=findViewById(R.id.thank_you_back);

        thank_you_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}