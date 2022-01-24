package com.master.design.rashnanthi.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.R;

public class VerifyActivity extends AppCompatActivity {
    private Context context;
    AppController appController;
    Button submit_otpBtn, edit_numberBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        submit_otpBtn = findViewById(R.id.submit_otpBtn);
        edit_numberBtn = findViewById(R.id.edit_numberBtn);


        appController = (AppController) getApplicationContext();

        edit_numberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        submit_otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                intent.putExtra("something", "yep");
                intent.putExtra("chirag", "yepp");
                startActivity(intent);

            }
        });


    }


}
