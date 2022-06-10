package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.ForgotPasswordRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Forgot_Password_Activity extends AppCompatActivity {
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;


    @NotEmpty
    @BindView(R.id.email_ET11)
    EditText email_ET11;

    @OnClick(R.id.submitB)
    public void submit() {
        if (connectionDetector.isConnectingToInternet()) {
            boolean correct = true;


            if (email_ET11.getText().toString().equalsIgnoreCase("")) {
                correct = false;
                Helper.showToast(Forgot_Password_Activity.this, getString(R.string.enter_email));
            } else if (correct) {


                progress = dialogUtil.showProgressDialog(Forgot_Password_Activity.this, getString(R.string.please_wait));

                String email11 = user.getEmail();
                appController.paServices.ForgotPassword(email_ET11.getText().toString(), new Callback<ForgotPasswordRootDM>() {
                    @Override

                    public void success(ForgotPasswordRootDM forgotPasswordRootDM, Response response) {
                        progress.dismiss();
                        if (forgotPasswordRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            Helper.showToast(Forgot_Password_Activity.this, "password sent on registered email");

                        } else
                            Helper.showToast(Forgot_Password_Activity.this, "password did not sent on registered email");
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        progress.dismiss();
                        Log.e("error", retrofitError.toString());

                    }
                });
            }
        } else
            Helper.showToast(Forgot_Password_Activity.this, getString(R.string.no_internet_connection));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Forgot_Password_Activity.this);

    }

}
