package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.EventRegisterDM;
import com.master.design.rashnanthi.DataModel.EventRegisterOutput;
import com.master.design.rashnanthi.DataModel.OtpScrenRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class VerifyActivity extends AppCompatActivity {
      Context context;
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;

    @BindView(R.id.resend_otpTxtTimer)
    TextView resend_otpTxtTimer;

    @BindView(R.id.edit_numberBtn)
    Button edit_numberBtn;

    @BindView(R.id.otp_view)
    in.aabhasjindal.otptextview.OtpTextView otpTextView;

    @BindView(R.id.submit_otpBtn)
    Button submit_otpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(VerifyActivity.this);

    }

    @OnClick(R.id.submit_otpBtn)
    public void SubmitOtp() {

        VerifyAPI();

//                Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
//                intent.putExtra("something", "yep");
//                intent.putExtra("chirag", "yepp");
//                startActivity(intent);

    }

    @OnClick(R.id.resend_otpTxtTimer)
    public void ResendOtp() {
    }

    @OnClick(R.id.edit_numberBtn)
    public void EditNumber() {
    }

    public void VerifyAPI() {

        if(connectionDetector.isConnectingToInternet())
        {
            progress = dialogUtil.showProgressDialog(VerifyActivity.this,getString(R.string.please_wait));

//            String mobile =String.valueOf(user.getId()) ;

            appController.paServices.OtpVerify("221212", otpTextView.getOTP() , new Callback<OtpScrenRootDM>() {
                @Override

                public void success ( OtpScrenRootDM otpScrenRootDM, Response response ) {
                    progress.dismiss();
                    if(otpScrenRootDM.getOutput().getSuccess().equalsIgnoreCase("1"))
                    {
//                        Helper.showToast(VerifyActivity.this,otpScrenRootDM.getOutput().getSuccess());
                        startActivity(new Intent(VerifyActivity.this,LoginActivity.class));
                    }else
                        Helper.showToast(VerifyActivity.this,otpScrenRootDM.getOutput().getSuccess());
                }

                @Override
                public void failure ( RetrofitError retrofitError ) {
                    progress.dismiss();
                    Log.e("error",retrofitError.toString());

                }
            });
        }else
            Helper.showToast(VerifyActivity.this,getString(R.string.no_internet_connection));


    }


    public void ResendOtpAPI() {
//        if (connectionDetector.isConnectingToInternet()) {
//
//            progress = dialogUtil.showProgressDialog(VerifyActivity.this,getString(R.string.please_wait));
//
//            String customerId =String.valueOf(user.getId()) ;
//
//            appController.paServices.CustomerResendOtp(customerId, new Callback<CustomerResendOtpDM>() {
//                @Override
//                public void success ( CustomerResendOtpDM customerResendOtpDM, Response response ) {
//                    progress.dismiss();
//                    if (customerResendOtpDM.getStatus().equalsIgnoreCase("1")) {
////                        Helper.showToast(SignUpActivity.this,customerRegisterDM.getMessage());
//
//                        startActivity(new Intent(VerifyActivity.this, SuccessfullyActivity.class));
//
//
//                    } else
//                        Helper.showToast(VerifyActivity.this, customerResendOtpDM.getMessage());
//                }
//
//                @Override
//                public void failure ( RetrofitError retrofitError ) {
//                    progress.dismiss();
//                    Log.e("error", retrofitError.toString());
//
//                }
//            });
//
//        } else
//            Helper.showToast(VerifyActivity.this,getString(R.string.no_internet_connection));

    }

    public  void IntentValuesFromSignupActivity(){
        Intent intent = getIntent();

        String Event = intent.getStringExtra("EventCreator");
        String Coach = intent.getStringExtra("CoachCreator");

        if (Event.equals("1")) {
            startActivity(new Intent(VerifyActivity.this,LoginActivity.class));

        } else if (Coach.equals("2")) {
            startActivity(new Intent(VerifyActivity.this, LoginActivity.class));

        }
    }


    public void Otp() {


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
        otpTextView.getOtpListener();  // retrieves the current OTPListener (null if nothing is set)
        otpTextView.requestFocusOTP();    //sets the focus to OTP box (does not open the keyboard)
//        otpTextView.setOTP(otpString);	// sets the entered otpString in the Otp box (for case when otp is retreived from SMS)
        otpTextView.getOTP();    // retrieves the OTP entered by user (works for partial otp input too)
        otpTextView.showSuccess();    // shows the success state to the user (can be set a bar color or drawable)
        otpTextView.showError();    // shows the success state to the user (can be set a bar color or drawable)
        otpTextView.resetState();    // brings the views back to default state (the state it was at input)

    }

}
