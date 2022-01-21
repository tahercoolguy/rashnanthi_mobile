package com.master.design.rashnanthi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.Login;
//import com.google.android.material.textfield.TextInputEditText;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.SignUpDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

public class LoginActivity extends AppCompatActivity {
    AppController appController;

//    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
//    DialogUtil dialogUtil;

    Button registerBtn;


    @BindView(R.id.countryImg)
    ImageView CountryImg;

    @BindView(R.id.countrycodeET)
    EditText CountrycodeET;

    @BindView(R.id.mobileET)
    EditText MobileET;

    @BindView(R.id.forget_PasswordTxt)
    TextView Forget_PasswordTxt;

    @BindView(R.id.loginBtn)
    Button LoginBtn;

    @BindView(R.id.new_UserTxt)
    Button New_UserTxt;

//    @BindView(R.id.registerBtn)
//    Button registerBtn;


//
//    @OnClick(R.id.loginBtn)
//    public void LoginBtn()
//    {
//       startActivity(new Intent(LoginActivity.this,MainActivity.class));
//    }

//    @OnClick(R.id.registerBtn)
//    public void RegisterBtn()
//    {
//        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
//    }


//    @NotEmpty
//    @Email
//    @BindView(R.id.emailET)
//    EditText EmailET;
//
//    @NotEmpty
//    @BindView(R.id.passwordET)
//    EditText passwordET;
//
//    @OnClick(R.id.signInBtn)
//    public void SignIn()
//    {
//        try {
//            if (connectionDetector.isConnectingToInternet()) {
//                isValid();
//                if (o) {
//                    MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
//
//                    multipartTypedOutput.addPart("email",new TypedString(EmailET.getText().toString()));
//                    multipartTypedOutput.addPart("password",new TypedString(passwordET.getText().toString()));
//
//                    appController.paServices.SignUp(multipartTypedOutput, new Callback<SignUpDM>() {
//                        @Override
//                        public void success(SignUpDM signUpDM, Response response) {
//                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void failure(RetrofitError error) {
//
//                        }
//                    });
//                }
//            } else
//                Helper.showToast(LoginActivity.this, getString(R.string.no_internet_connection));
//        }catch (Exception e)
//        {
//
//        }
//
//    }
//
//    @OnClick(R.id.signUpBtn)
//    public void signUpBtn()
//    {
//        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.guestBtn)
//    public void guestBtn()
//    {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);
//        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(LoginActivity.this);
//        validator=new Validator(this);
//        validator.setValidationListener(this);

        registerBtn=findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
             }
        });


    }

//    @Override
//    public void onValidationSucceeded() {
//
//    }
//    boolean o=true;
//    Validator validator;
//
//
//    @Override
//    public void onValidationFailed(List<ValidationError> errors) {
//        for (ValidationError error : errors) {
//            View view = error.getView();
//            String message = error.getCollatedErrorMessage(this);
//            o=false;
//            // Display error messages ;)
//            if (view instanceof TextInputEditText) {
//                ((TextInputEditText) view).setError(message);
//            } else {
//                Helper.showToast(LoginActivity.this,message);
//            }
//        }
//    }

//    public void isValid() {
//        boolean done = true;
//        o=true;
//        validator.validate();
//        //o=done;
//        if(!done)
//            o=done;
//
//    }

}
