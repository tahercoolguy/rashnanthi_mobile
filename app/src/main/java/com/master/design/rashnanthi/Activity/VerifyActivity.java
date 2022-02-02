package com.master.design.rashnanthi.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.R;

import butterknife.BindView;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class VerifyActivity extends AppCompatActivity {
    private Context context;
    AppController appController;
    Button submit_otpBtn, edit_numberBtn;
    private OtpTextView otpTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        submit_otpBtn = findViewById(R.id.submit_otpBtn);
        edit_numberBtn = findViewById(R.id.edit_numberBtn);

        otpTextView = findViewById(R.id.otp_view);
//        otpTextView.setOtpListener(new OTPListener() {
//            @Override
//            public void onInteractionListener() {
//                // fired when user types something in the Otpbox
//            }
//            @Override
//            public void onOTPComplete(String otp) {
//                // fired when user has entered the OTP fully.
//                Toast.makeText(VerifyActivity.this, "The OTP is " + otp,  Toast.LENGTH_SHORT).show();
//            }
//        });


        appController = (AppController) getApplicationContext();

        edit_numberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        otpTextView.getOtpListener();  // retrieves the current OTPListener (null if nothing is set)
        otpTextView.requestFocusOTP();	//sets the focus to OTP box (does not open the keyboard)
//        otpTextView.setOTP(otpString);	// sets the entered otpString in the Otp box (for case when otp is retreived from SMS)
        otpTextView.getOTP();	// retrieves the OTP entered by user (works for partial otp input too)
        otpTextView.showSuccess();	// shows the success state to the user (can be set a bar color or drawable)
        otpTextView.showError();	// shows the success state to the user (can be set a bar color or drawable)
        otpTextView.resetState();	// brings the views back to default state (the state it was at input)

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
