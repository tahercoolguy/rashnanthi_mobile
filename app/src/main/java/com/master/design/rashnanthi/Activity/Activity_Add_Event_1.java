package com.master.design.rashnanthi.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.AddEventByCreatorRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Activity_Add_Event_1 extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;

    @NotEmpty
    @BindView(R.id.dateTxt)
    TextView dateTxt;

    @NotEmpty
    @BindView(R.id.dateRL)
    RelativeLayout dateRL;


    @NotEmpty
    @BindView(R.id.wtspcountryImg)
    ImageView wtspcountryImg;

    @NotEmpty
    @BindView(R.id.wtspcodeTxt)
    TextView wtspcodeTxt;

    @NotEmpty
    @BindView(R.id.wtsapRL)
    RelativeLayout wtsapRL;

    @NotEmpty
    @BindView(R.id.snap_ET)
    EditText snap_ET;

    @NotEmpty
    @BindView(R.id.insta_ET)
    EditText insta_ET;

    @NotEmpty
    @BindView(R.id.wesite_ET)
    TextView wesite_ET;

    @NotEmpty
    @BindView(R.id.mobile__ET)
    TextView mobile__ET;

    @NotEmpty
    @BindView(R.id.country_Img)
    ImageView country_Img;

    @NotEmpty
    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;

//    @NotEmpty
//    @BindView(R.id.spinnerCountryBottomRL)
//    RelativeLayout spinnerCountryBottomRL;

//    @NotEmpty
//    @BindView(R.id.spinnerBottomRL)
//    RelativeLayout spinnerBottomRL;

    @NotEmpty
    @BindView(R.id.country_Img1)
    ImageView country_Img1;

    @NotEmpty
    @BindView(R.id.spinnerCountryBottomRL1)
    RelativeLayout spinnerCountryBottomRL1;

    @NotEmpty
    @BindView(R.id.country_spinner_Txt1)
    TextView country_spinner_Txt1;


    @NotEmpty
    @BindView(R.id.pay_now_Btn)
    Button pay_now_Btn;

    @NotEmpty
    @BindView(R.id.continue_Btn)
     Button continue_Btn;

    @NotEmpty
    @BindView(R.id.post_for_free_nowBtn)
    Button post_for_free_nowBtn;

    String EventId;



    @OnClick(R.id.pay_now_Btn)
    public void PayNow() {
        startActivity(new Intent(Activity_Add_Event_1.this,Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.continue_Btn)
    public void Continue() {
        startActivity(new Intent(Activity_Add_Event_1.this,Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.post_for_free_nowBtn)
    public void PostForFreeNow() {
        AddEventByCreatorAPI();
     }

    LinearLayout ad_more_eventtLL, website_LL;
    Button add_more_eventBtn;
    TextView your_post_will_beTXt;
     LinearLayout radioBtn_Term_condition;
     private ArrayList<Country_CodeDM> country_codeDMS;
    private ArrayList<Country_NameDM> countryNameDMS;
    RelativeLayout add_img_video_1_RL, add_img_video_2_RL, add_img_video_3_RL, add_img_video_4_RL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_1);
        ButterKnife.bind(this);

        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Activity_Add_Event_1.this);

        Binding();
        ad_more_eventtLL = findViewById(R.id.ad_more_eventtLL);
        your_post_will_beTXt = findViewById(R.id.your_post_will_beTXt);
        website_LL = findViewById(R.id.website_LL);
        add_more_eventBtn = findViewById(R.id.add_more_eventBtn);
        radioBtn_Term_condition = findViewById(R.id.radioBtn_Term_condition);

        add_img_video_3_RL = findViewById(R.id.add_img_video_3_RL);
        add_img_video_1_RL = findViewById(R.id.add_img_video_1_RL);
        add_img_video_2_RL = findViewById(R.id.add_img_video_2_RL);
        add_img_video_4_RL = findViewById(R.id.add_img_video_4_RL);

//
//        add_event_pay_back=findViewById(R.id.add_event_pay_back);
//
//        add_event_pay_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        pay_now_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Activity_Add_Event_1.this,Add_Event_Pay_Now.class));
//            }
//        });
//
//        continue_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Activity_Add_Event_1.this,Add_Event_Pay_Now.class));
//
//            }
//        });
//
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
//        adapter_country_name_spinner = new Adapter_Country_Name_Spinner( this,countryNameDMS);
//
//
//        country_Name_SP2.setAdapter(adapter_country_name_spinner);
//        country_Name_Sp.setAdapter(adapter_country_name_spinner);
//
//
//        country_code_Sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//
//        country_Name_SP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//
//
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
//        adapter_country_code_only_spinner = new Adapter_Country_Code_Only_Spinner( this,country_codeDMS);
//
//
//        country_code_Sp.setAdapter(adapter_country_code_only_spinner);
//


        add_img_video_1_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/* video/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/* video/*");
                startActivityForResult(pickIntent, 3);
            }

        });


        add_img_video_2_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });

        add_img_video_3_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });

        add_img_video_4_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });

        add_more_eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifAddMoreEventClicked1 = true;
                NotificationVisibilityFunction1();
            }
        });

        ad_more_eventtLL.setVisibility(View.GONE);
        your_post_will_beTXt.setVisibility(View.GONE);
        continue_Btn.setVisibility(View.GONE);
        NotificationVisibilityFunction1();
    }


    boolean ifAddMoreEventClicked1 = false;

    public void NotificationVisibilityFunction1() {

        if (ifAddMoreEventClicked1) {

            ad_more_eventtLL.setVisibility(View.VISIBLE);
            add_more_eventBtn.setVisibility(View.VISIBLE);
            your_post_will_beTXt.setVisibility(View.VISIBLE);
            radioBtn_Term_condition.setVisibility(View.VISIBLE);
            continue_Btn.setVisibility(View.VISIBLE);
            post_for_free_nowBtn.setVisibility(View.GONE);
            pay_now_Btn.setVisibility(View.GONE);
            website_LL.setVisibility(View.GONE);

        }


    }


    public void AddEventByCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

            appController.paServices.AddEventByCreator(dateTxt.getText().toString(),wtspcodeTxt.getText().toString(),mobile__ET.getText().toString(),
                    snap_ET.getText().toString(),insta_ET.getText().toString(),wesite_ET.getText().toString(),country_spinner_Txt.getText().toString()
                    , String.valueOf(R.drawable.my_event_img_1), String.valueOf(R.drawable.my_event_img_2),"1",user.getName(),"1", new Callback<AddEventByCreatorRootDM>() {

                @Override

                public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
                    progress.dismiss();
                    if (addEventByCreatorRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.shwToast(Activity_Add_Event_1.this,customerRegisterDM.getMessage());
//                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));


                        Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());
                        Intent intent = new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class);
                        intent.putExtra("eventid",addEventByCreatorRootDM.getOutput().getEventid());
                        startActivity(intent);
                    } else
                        Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Activity_Add_Event_1.this, getString(R.string.no_internet_connection));

    }


    int mYear, mMonth, mDay, expiryDay;

    @OnClick(R.id.dateRL)
    public void date() {
        CalenderDataPicker();

    }



    public void CalenderDataPicker() {
        // Get Current Date
        final java.util.Calendar c = java.util.Calendar.getInstance();
        mYear = c.get(java.util.Calendar.YEAR);
        mMonth = c.get(java.util.Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(Activity_Add_Event_1.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        dateTxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "dd-MM-yyyy";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();

    @OnClick(R.id.wtsapRL)
    public void WhatsappCodeCountry() {

         bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                wtspcodeTxt.setText(data.get(position).getCallingcode());
//                wtspcountryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));
                Picasso.get().load(AppController.base_image_url +data.get(position).getImage()).into(wtspcountryImg);

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
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt.setText(data.get(position).getTitle());
//                country_Img.setImageResource(Integer.parseInt(data.get(position).getImage()));
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


    @OnClick(R.id.spinnerCountryBottomRL1)
    public void SpinnerCountry1() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt1.setText(data.get(position).getTitle());
//                country_Img1.setImageResource(Integer.parseInt(data.get(position).getImage()));
                Picasso.get().load(AppController.base_image_url +data.get(position).getImage()).into(country_Img1);
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
                        Helper.showToast(Activity_Add_Event_1.this, "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
    }

}
