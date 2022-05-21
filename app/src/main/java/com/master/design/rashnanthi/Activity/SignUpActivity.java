package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.EventRegisterDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

public class SignUpActivity extends AppCompatActivity {
    AppController appController;
    Context context;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int IMAGE_PICKER_SELECT1 = 2;
    private static final int FILE_PICKER_SELECT = 3;
    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    private ArrayList<Country_NameDM> countryNameDMS;
    private ArrayList<Country_CodeDM> country_codeDMS;

    @BindView(R.id.country_spinnerET)
    TextView country_spinnerET;

  @BindView(R.id.country_Img)
    ImageView country_Img;



  @BindView(R.id.mobilecountryImg)
    ImageView mobilecountryImg;




    @BindView(R.id.spinnerBottomRL)
    RelativeLayout spinnerBottomRL;

    @BindView(R.id.spinnerBottom_RL)
    RelativeLayout spinnerBottom_RL;


  @BindView(R.id.spinnerCountryBottomRL)
    RelativeLayout spinnerCountryBottomRL;


     @BindView(R.id.country_spinner_ET)
    TextView country_spinner_ET;

     @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;








//    Spinner country_name_spinner, mobile_countrycode_Sp, wtsap_countrycode_Sp;
    CircleImageView dp;

    ImageView profileImg, cameraImg, back_from_register_page;

//    ImageView back_from_register_page;

    LinearLayout testing, testing1;
    TextView event, coach, nameET, emailET, passwordET, confirm_passwordET;
    Button register_NowBtn;
    EditText snap_id_ET, insta_id_ET, mobileET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(SignUpActivity.this);
        dialogUtil = new DialogUtil();
        Binding();

//        country_name_spinner = findViewById(R.id.country_name_spinner);

        back_from_register_page = findViewById(R.id.back_from_register_page);

        back_from_register_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        testing = findViewById(R.id.testing);
        testing1 = findViewById(R.id.testing1);
        event = findViewById(R.id.eventTxt);
        coach = findViewById(R.id.coachTxt);
        register_NowBtn = findViewById(R.id.register_NowBtn);
        profileImg = findViewById(R.id.profileImg);
        cameraImg = findViewById(R.id.cameraImg);
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        confirm_passwordET = findViewById(R.id.confirm_passwordET);
        snap_id_ET = findViewById(R.id.snap_id_ET);
        insta_id_ET = findViewById(R.id.insta_id_ET);
        mobileET = findViewById(R.id.mobileET);
        dp = findViewById(R.id.profile_RoundedImgView);
        cameraImg = findViewById(R.id.cameraImg);
//
//        mobile_countrycode_Sp = findViewById(R.id.mobile_countrycode_Sp);
//        wtsap_countrycode_Sp = findViewById(R.id.wtsap_countrycode_Sp);

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
//        mobile_countrycode_Sp.setAdapter(adapter_country_code_only_spinner);
//        wtsap_countrycode_Sp.setAdapter(adapter_country_code_only_spinner);


//        ArrayList<Country_NameDM> countryNameDMS;
//
//
//        countryNameDMS = new ArrayList<>();
//        countryNameDMS.add(new Country_NameDM("Kuwait"));
//        countryNameDMS.add(new Country_NameDM("Oman"));
//        countryNameDMS.add(new Country_NameDM("Saudi Arabia"));
//        countryNameDMS.add(new Country_NameDM("Qatar"));
//        countryNameDMS.add(new Country_NameDM("Bahrain"));
//
//
//        Adapter_Country_Name_Spinner adapter_country_name_spinner;
//
//        adapter_country_name_spinner = new Adapter_Country_Name_Spinner(this, countryNameDMS);
//
//
//        country_name_spinner.setAdapter(adapter_country_name_spinner);
//
//
//        country_name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        VisibilityFunction();


//                Intent i = new Intent(getApplicationContext(),Menu_1_Fragment.class);
//



        //        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);



        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCoach = true;

                VisibilityFunction();
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCoach = false;

                VisibilityFunction();
            }
        });


        register_NowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ifCoach) {
                    CoachRegisterAPI();
                } else  {
                    EventsCreatorAPI();
                }
            }
        });

        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImageFromGallery();
            }
        });

    }

    public void SelectImageFromGallery() {
        ImagePicker.with(this)
//                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        dp.setImageURI(uri);

    }

//
//    public void EventsCreators() {
//        try {
//            if (connectionDetector.isConnectingToInternet()) {
//                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
////                appController.paServices.CustomerRegister("0", "1", "12", "12", "123",
////                        "123", "123", "12", "2", refreshedToken, new Callback<EventRegisterDM>() {
////                            @Override
////                            public void success(EventRegisterDM eventRegisterDM, Response response) {
////                                progress.dismiss();
////                                if (eventRegisterDM.getEventRegisterOutput().getMessage().equalsIgnoreCase("1")) {
////
////                                    startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
////                                    finish();
////
////
////                                } else
////                                    Helper.showToast(SignUpActivity.this, eventRegisterDM.getEventRegisterOutput().getMessage());
////
////                            }
////
////                            @Override
////                            public void failure(RetrofitError error) {
////                                Log.e("String", error.toString());
////                            }
////                        });
//            } else
//                Helper.showToast(SignUpActivity.this, getString(R.string.no_internet_connection));
//
//        } catch (Exception e) {
//            Log.e("String", e.toString());
//        }
//
//    }


    public void EventsCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("fullname", new TypedString(nameET.getText().toString()));
            multipartTypedOutput.addPart("image", new TypedString("12"));
            multipartTypedOutput.addPart("email", new TypedString(emailET.getText().toString()));
            multipartTypedOutput.addPart("password", new TypedString(passwordET.getText().toString()));
            multipartTypedOutput.addPart("confpassword", new TypedString(confirm_passwordET.getText().toString()));
            multipartTypedOutput.addPart("mobile", new TypedString(mobileET.getText().toString()));
            multipartTypedOutput.addPart("countrycode", new TypedString("1"));
            multipartTypedOutput.addPart("creatorcoach", new TypedString("1"));

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            multipartTypedOutput.addPart("deviceid", new TypedString(refreshedToken));
            multipartTypedOutput.addPart("devicetype", new TypedString("2"));



            appController.paServices.EventCreatorReg(multipartTypedOutput, new Callback<EventRegisterDM>() {
                @Override
                public void success(EventRegisterDM eventRegisterDM, Response response) {
                    if (eventRegisterDM.getOutput().getSuccess().equalsIgnoreCase("1")) {



                        user.setId(Integer.valueOf(eventRegisterDM.getOutput().getUserid()));
 //                            Helper.showToast(SignUpActivity.this, eventRegisterDM.getOutput().getMessage());

                        Intent intent = new Intent(SignUpActivity.this, VerifyActivity.class);
                        intent.putExtra("EventCreator", eventRegisterDM.getOutput().getCreatorcoach());
                        startActivity(intent);

//                        startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
//                        finish();


                    } else
                        Helper.showToast(SignUpActivity.this, eventRegisterDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("Error", error.toString());
                }
            });
        } else
            Helper.showToast(SignUpActivity.this, getString(R.string.no_internet_connection));

    }

    public void CoachRegisterAPI() {
        try {
            if (connectionDetector.isConnectingToInternet()) {


                progress = dialogUtil.showProgressDialog(SignUpActivity.this, getString(R.string.please_wait));
                MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
                multipartTypedOutput.addPart("creatorcoach", new TypedString("2"));
                multipartTypedOutput.addPart("fullname", new TypedString(nameET.getText().toString()));
                multipartTypedOutput.addPart("image", new TypedString("12"));
                multipartTypedOutput.addPart("email", new TypedString(emailET.getText().toString()));
                multipartTypedOutput.addPart("password", new TypedString(passwordET.getText().toString()));
                multipartTypedOutput.addPart("confpassword", new TypedString(confirm_passwordET.getText().toString()));
                multipartTypedOutput.addPart("mobile", new TypedString(mobileET.getText().toString()));
                multipartTypedOutput.addPart("countrycode", new TypedString("91"));
                multipartTypedOutput.addPart("snapchat", new TypedString(snap_id_ET.getText().toString()));
                multipartTypedOutput.addPart("instagram", new TypedString(insta_id_ET.getText().toString()));
                multipartTypedOutput.addPart("countryid", new TypedString("5"));

                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                multipartTypedOutput.addPart("deviceid", new TypedString(refreshedToken));
                multipartTypedOutput.addPart("devicetype", new TypedString("2"));


                appController.paServices.CoachReg(multipartTypedOutput, new Callback<EventRegisterDM>() {
                    @Override
                    public void success(EventRegisterDM eventRegisterDM, Response response) {
                        progress.dismiss();
                        if (eventRegisterDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            user.setId(Integer.parseInt(eventRegisterDM.getOutput().getUserid()));

                            user.setEmail(emailET.getText().toString());

                            Intent intent = new Intent(SignUpActivity.this, VerifyActivity.class);
                            intent.putExtra("CoachCreator", eventRegisterDM.getOutput().getCreatorcoach());
                            startActivity(intent);

//                            startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
//                            finish();
                        } else
                            Helper.showToast(SignUpActivity.this, eventRegisterDM.getOutput().getMessage());

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("String", error.toString());
                        progress.dismiss();
                    }
                });
            } else
                Helper.showToast(SignUpActivity.this, getString(R.string.no_internet_connection));
        } catch (Exception e) {
            Log.e("String", e.toString());
        }
    }


    boolean ifCoach = false;

    public void VisibilityFunction() {
        if (ifCoach) {
            coach.setBackground(getDrawable(R.drawable.rounded_corner_black_border));
            event.setBackground(getDrawable(R.drawable.rounded_corner_white));

            testing.setVisibility(View.VISIBLE);
            testing1.setVisibility(View.VISIBLE);
        } else {
            event.setBackground(getDrawable(R.drawable.rounded_corner_black_border));
            coach.setBackground(getDrawable(R.drawable.rounded_corner_white));

            testing.setVisibility(View.GONE);
            testing1.setVisibility(View.GONE);
        }
    }





    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne=new ArrayList<>();
    ArrayList<String> approvalTwo=new ArrayList<>();

    @OnClick(R.id.spinnerBottom_RL)
    public void Spinner_Country() {
        bottomForAll= new BottomForAll();
        bottomForAll.arrayList=approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_ET.setText(data.get(position).getCallingcode());

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


    @OnClick(R.id.spinnerCountryBottomRL)
    public void Spinner__Country() {
        bottomForAll= new BottomForAll();
        bottomForAll.arrayList=approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt.setText(data.get(position).getTitle());
                Picasso.get().load(AppController.base_image_url +data.get(position).getImage()).into(country_Img);

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



    @OnClick(R.id.spinnerBottomRL)
    public void SpinnerCountry() {
        bottomForAll= new BottomForAll();
        bottomForAll.arrayList=approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinnerET.setText(data.get(position).getCallingcode());
//                country_Img.setImageResource(Integer.parseInt(data.get(position).getImage()));
                Picasso.get().load(AppController.base_image_url +data.get(position).getImage()).into(mobilecountryImg);

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

    ArrayList<CountryData> data=new ArrayList<>();
    public void Binding()
    {
        if(connectionDetector.isConnectingToInternet())
        {
            appController.paServices.Countries(new Callback<CountryRootDM>() {
                @Override
                public void success(CountryRootDM countryRootDM, Response response) {
                    if(countryRootDM.getOutput().getSuccess().equalsIgnoreCase("1"))
                    {
                        data = countryRootDM.getOutput().getData();
                        for (CountryData area:countryRootDM.getOutput().getData()
                        ) {
                            approvalOne.add(area);
                            if (approvalOne.get(0).getId().equalsIgnoreCase("1")) {
                                country_spinnerET.setText(data.get(0).getCallingcode());
                                country_spinner_ET.setText(data.get(0).getCallingcode());
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(mobilecountryImg);
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img);
                            }
                        }
                    }else
                        Helper.showToast(SignUpActivity.this,"Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String",error.toString());
                }
            });
        }
    }

}






