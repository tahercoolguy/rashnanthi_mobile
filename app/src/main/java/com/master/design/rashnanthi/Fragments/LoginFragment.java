package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Activity.Forgot_Password_Activity;
import com.master.design.rashnanthi.Activity.LoginActivity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginFragment extends Fragment {

    private View rootView;
    private Context context;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
     DialogUtil dialogUtil;
    Dialog progress;
     User user;
    String coachcreator, eventcreator;


    private ArrayList<Country_CodeDM> country_codeDMS;
    Spinner code_spinner;


    @BindView(R.id.register_now_Btn)
    Button register_now_Btn;

    @BindView(R.id.mobileET)
    EditText mobileET;

    @BindView(R.id.country_spinnerET)
    TextView country_spinnerET;


    @BindView(R.id.spinnerBottomRL)
    RelativeLayout spinnerBottomRL;


    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.backlogin)
    ImageView backlogin;

    @BindView(R.id.countryImg)
    ImageView countryImg;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.forget_PasswordTxt)
    TextView forget_PasswordTxt;

    @OnClick(R.id.forget_PasswordTxt)
    public void ForgotPassword() {
        Intent intent = new Intent(context, Forgot_Password_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.loginBtn)
    public void LoginButton() {

        LogInAPI();
    }

    public void LogInAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            progress = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));

            appController.paServices.Login(country_spinnerET.getText().toString(), mobileET.getText().toString(),
                    passwordET.getText().toString(), new Callback<LoginRootDM>() {
                        @Override
                        public void success(LoginRootDM loginRootDM, Response response) {
                            progress.dismiss();
                            if (loginRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.shwToast(context,customerRegisterDM.getMessage());

                                user.setId(Integer.parseInt(loginRootDM.getOutput().getData().get(0).getId()));
                                user.setName(loginRootDM.getOutput().getData().get(0).getFullname());
                                user.setCreatorcoach(loginRootDM.getOutput().getData().get(0).getCreatorcoach());
                                String neemail = loginRootDM.getOutput().getData().get(0).getEmail();
                                user.setEmail(neemail);


                                coachcreator = String.valueOf(loginRootDM.getOutput().getData().get(0).getCreatorcoach().equalsIgnoreCase("2"));
                                eventcreator = String.valueOf(loginRootDM.getOutput().getData().get(0).getCreatorcoach().equalsIgnoreCase("1"));

                                if (coachcreator!=null) {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("chirag2", coachcreator);
                                    startActivity(intent);
                                    //
                                } else if (eventcreator!=null) {
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("chirag1", eventcreator);
                                    startActivity(intent);
                                    //
                                }


//                                if(eventcreator=="1"){
//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    intent.putExtra("chirag1", eventcreator);
//                                    startActivity(intent);
//                                    //
//
//                                }else if(coachcreator=="2"){
//
//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    intent.putExtra("chirag2", coachcreator);
//                                    startActivity(intent);
//                                    //
//                                }

//                                if (user.getCoachOrEvent().equalsIgnoreCase("1")) {
//
//                                    startActivity(new Intent(context, MainActivity.class));
//                                } else {
//                                    startActivity(new Intent(context, MainActivity.class));
//                                }


//                                if (String.valueOf(user.getCoachOrEvent().equalsIgnoreCase("1"))=="1") {
//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    intent.putExtra("EventCreator", "1");
//                                    startActivity(intent);
//                                    //
//
//
//                                } else {
//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    intent.putExtra("CoachCreator", "2");
//                                    startActivity(intent);
//                                    //
//                                }
                                startActivity(new Intent(context, MainActivity.class));

                            } else
                                Helper.showToast(context, "entered mobile,password or country code incorrect");

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            progress.dismiss();

                            Log.e("error", retrofitError.toString());

                        }
                    });

        } else
            Helper.showToast(context, getString(R.string.no_internet_connection));


    }


//    public void ForgotPasswordAPI() {
//
//        if (connectionDetector.isConnectingToInternet()) {
//
//            progress = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));
//
//                String   email11= user.getEmail();
//            appController.paServices.ForgotPassword(email11, new Callback<ForgotPasswordRootDM>() {
//                @Override
//
//                public void success(ForgotPasswordRootDM forgotPasswordRootDM, Response response) {
//                    progress.dismiss();
//                    if (forgotPasswordRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.showToast(context, "password sent on registered email");
//
//                    } else
//                        Helper.showToast(context, "password did not sent on registered email");
//                }
//
//                @Override
//                public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();
//                    Log.e("error", retrofitError.toString());
//
//                }
//            });
//        } else
//            Helper.showToast(context, getString(R.string.no_internet_connection));
//
//    }


    @OnClick(R.id.backlogin)
    public void Back() {
        //
    }


    @OnClick(R.id.register_now_Btn)
    public void RegisterBtn() {
        startActivity(new Intent(context, SignUpActivity.class));
        //
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();
            Binding();

            setClickListeners();
            setDetails();
        }
        return rootView;
    }




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
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(countryImg);

//                AreaID = data.get(selected).getId();
//                for (CountryData s:data
//                ) {
//                    if(s.getCallingcode().equals((String) object))
//                        AreaID = s.getId();
//                }


            }
        });


        bottomForAll.show(getChildFragmentManager(), "bottomSheetCountry");
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
                            if (approvalOne.get(0).getId().equalsIgnoreCase("1")) {
                                country_spinnerET.setText(data.get(0).getCallingcode());
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(countryImg);
                            }
                        }
                    } else
                        Helper.showToast(context, "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
    }



    private void idMapping() {


    }

    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setDetails() {
        ShowProgress();
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                DismissProgress();
            }
        }, 1500);




    }

    public void ShowProgress()
    {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress()
    {
        progress_bar.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_back).setVisible(false);
    }
}