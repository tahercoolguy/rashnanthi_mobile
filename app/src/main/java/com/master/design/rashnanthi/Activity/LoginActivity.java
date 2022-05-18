package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.ForgotPasswordRootDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.Fragments.Menu_1_Fragment;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    AppController appController;
    DialogUtil dialogUtil;
    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    Context context;
    private ArrayList<Country_CodeDM> country_codeDMS;
    Spinner code_spinner;
    String email;

    @BindView(R.id.register_now_Btn)
    Button register_now_Btn;

    @BindView(R.id.mobileET)
    EditText mobileET;

    @BindView(R.id.country_spinnerET)
    EditText country_spinnerET;


    @BindView(R.id.spinnerBottomRL)
    RelativeLayout spinnerBottomRL;


    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.backlogin)
    ImageView backlogin;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.forget_PasswordTxt)
    TextView forget_PasswordTxt;

    @OnClick(R.id.forget_PasswordTxt)
    public void ForgotPassword() {
        ForgotPasswordAPI();
    }

    @OnClick(R.id.loginBtn)
    public void LoginButton() {

        LogInAPI();
    }

    public void LogInAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            progress = dialogUtil.showProgressDialog(LoginActivity.this, getString(R.string.please_wait));

            appController.paServices.Login(country_spinnerET.getText().toString(), mobileET.getText().toString(), passwordET.getText().toString(), new Callback<LoginRootDM>() {

                @Override

                public void success(LoginRootDM loginRootDM, Response response) {
                    progress.dismiss();
                    if (loginRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.shwToast(LoginActivity.this,customerRegisterDM.getMessage());
                        user.setId(Integer.parseInt(loginRootDM.getOutput().getData().get(0).getId()));
                        user.setName(loginRootDM.getOutput().getData().get(0).getFullname());

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    } else
                        Helper.showToast(LoginActivity.this, "entered mobile,password or country code incorrect");

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(LoginActivity.this, getString(R.string.no_internet_connection));


    }


    public void ForgotPasswordAPI() {

        if (connectionDetector.isConnectingToInternet()) {

            progress = dialogUtil.showProgressDialog(LoginActivity.this, getString(R.string.please_wait));


            appController.paServices.ForgotPassword("fdsfd", new Callback<ForgotPasswordRootDM>() {
                @Override

                public void success(ForgotPasswordRootDM forgotPasswordRootDM, Response response) {
                    progress.dismiss();
                    if (forgotPasswordRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        Helper.showToast(LoginActivity.this, "password sent on registered email");

                    } else
                        Helper.showToast(LoginActivity.this, "password did not sent on registered email");
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(LoginActivity.this, getString(R.string.no_internet_connection));

    }


    @OnClick(R.id.backlogin)
    public void Back() {
        finish();
    }


    @OnClick(R.id.register_now_Btn)
    public void RegisterBtn() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(LoginActivity.this);
        dialogUtil = new DialogUtil();
        Binding();


//        ArrayList<Country_CodeDM> country_codeDMS;
//        country_codeDMS = new ArrayList<>();
//        country_codeDMS.add(new Country_CodeDM("+965"));
//        country_codeDMS.add(new Country_CodeDM("+968"));
//        country_codeDMS.add(new Country_CodeDM("+966"));
//        country_codeDMS.add(new Country_CodeDM("+974"));
//        country_codeDMS.add(new Country_CodeDM("+973"));
//
//        Adapter_Country_Code_Only_Spinner adapter_country_code_only_spinner;
//
//        adapter_country_code_only_spinner = new Adapter_Country_Code_Only_Spinner(this, country_codeDMS);
//
//
//        code_spinner.setAdapter(adapter_country_code_only_spinner);

//        validator=new Validator(this);
//        validator.setValidationListener(this);

//        registerBtn=findViewById(R.id.registerBtn);
//
//
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
//                finish();
//             }
//        });


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


    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


    @OnClick(R.id.spinnerBottomRL)
    public void SpinnerCountry() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinnerET.setText(data.get(position).getCallingcode());
//                AreaID = data.get(selected).getId();
//                for (CountryData s:data
//                ) {
//                    if(s.getCallingcode().equals((String) object))
//                        AreaID = s.getId();
//                }


            }
        });


        bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");
    }


    @OnClick(R.id.country_spinnerET)
    public void OpenBottom() {

    }

    ArrayList<CountryData> data = new ArrayList<>();

    public void Binding() {
        if (connectionDetector.isConnectingToInternet()) {
            appController.paServices.Countries(new Callback<CountryRootDM>() {
                @Override
                public void success(CountryRootDM countryRootDM, Response response) {
                    if (countryRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        data = countryRootDM.getOutput().getData();
                        for (CountryData area : countryRootDM.getOutput().getData()
                        ) {
                            approvalOne.add(area);
                        }
                    } else
                        Helper.showToast(LoginActivity.this, "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
    }

}
