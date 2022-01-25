package com.master.design.rashnanthi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.R;

public class Add_Event_Bill extends AppCompatActivity {

    ImageView bill;
    LinearLayout layout_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_bill);
        bill=findViewById(R.id.bill);
        layout_parent=findViewById(R.id.layout_parent);

        layout_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Event_Bill.this,Thank_you.class));
                finish();
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Event_Bill.this, Add_new_post_1.class));

            }
        });


    }


}
