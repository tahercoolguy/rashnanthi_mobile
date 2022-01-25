package com.master.design.rashnanthi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.R;

public class Add_new_post_1 extends AppCompatActivity {

    ImageView add_new_post_pay_back;
    Button pay_now_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post_1);
        add_new_post_pay_back = findViewById(R.id.add_new_post_pay_back);
        pay_now_Btn=findViewById(R.id.pay_now_Btn);

        pay_now_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_new_post_1.this,Add_Event_Pay_Now.class));
                finish();
            }
        });


        add_new_post_pay_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }


}
