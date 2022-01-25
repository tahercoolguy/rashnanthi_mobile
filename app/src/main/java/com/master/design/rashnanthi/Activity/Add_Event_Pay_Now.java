package com.master.design.rashnanthi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Fragments.Coach_Account_Fragment;
import com.master.design.rashnanthi.R;

public class Add_Event_Pay_Now extends AppCompatActivity {

    Button add_event_pay_now;

    TextView knet_circle,my_fatoorah_circle,visa_mastercard_circle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_pay_now);

        add_event_pay_now=findViewById(R.id.add_event_pay_now);
        visa_mastercard_circle=findViewById(R.id.visa_mastercard_circle);
        knet_circle=findViewById(R.id.knet_circle);

        my_fatoorah_circle=findViewById(R.id.my_fatoorah_circle);

        visa_mastercard_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifVisa_Mastercard =true;
                ifVisa_MastercardSelected();

            }
        });
        knet_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifKnet =true;
                ifKnetSelected();

            }
        });

        my_fatoorah_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifMY_Fatoora=true ;
                ifMY_FatooraSelected();
            }
        });



        add_event_pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Event_Pay_Now.this,Add_Event_Bill.class));
                finish();
            }
        });


    }



    boolean ifKnet = false;

    public  void ifKnetSelected(){
        knet_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));


    }

    boolean ifMY_Fatoora = false;

    public  void ifMY_FatooraSelected(){
        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));

    }

    boolean ifVisa_Mastercard = false;

    public  void ifVisa_MastercardSelected(){

        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));

    }


}
