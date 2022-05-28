package com.master.design.rashnanthi.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.AddEventByCreatorRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.EditEventRootDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.echodev.resizer.Resizer;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public class Activity_Add_Event_1 extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;

    private static final int IMAGE_PICKER_SELECT1 = 1;
    private static final int IMAGE_PICKER_SELECT2 = 2;
    private static final int IMAGE_PICKER_SELECT3 = 3;
    private static final int IMAGE_PICKER_SELECT4 = 4;
    String image1, image2, date, eventid, snapchat,instagram,wtsapcode,wtsapnumber,website,impcountry,creatorcoach,payorfree,status,postedby;

    MyEventData myEventData1;
    String CountryId;

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
    EditText wesite_ET;

    @NotEmpty
    @BindView(R.id.mobile__ET)
    EditText mobile__ET;

    @NotEmpty
    @BindView(R.id.country_Img)
    ImageView country_Img;

    @NotEmpty
    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;


    @NotEmpty
    @BindView(R.id.img1)
    ImageView img1;

    @NotEmpty
    @BindView(R.id.img2)
    ImageView img2;

    @NotEmpty
    @BindView(R.id.img3)
    ImageView img3;

    @NotEmpty
    @BindView(R.id.img4)
    ImageView img4;


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

        if(status!=null){
            EditAddEventCreatorAPI();

        }else{
            AddEventByCreatorAPI();
        }


//        startActivity(new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.continue_Btn)
    public void Continue() {

        if(status!=null){
            EditAddEventCreatorAPI();

        }else{
            AddEventByCreatorAPI();
        }
//        startActivity(new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.post_for_free_nowBtn)
    public void PostForFreeNow() {

        if(status!=null){
            EditAddEventCreatorAPI();
        }else{
            AddEventByCreatorAPI();
        }
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
        myEventData1 = new MyEventData();

        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Activity_Add_Event_1.this);

        Binding();
        DataGetFromAdapterIntent();

        ad_more_eventtLL = findViewById(R.id.ad_more_eventtLL);
        your_post_will_beTXt = findViewById(R.id.your_post_will_beTXt);
        website_LL = findViewById(R.id.website_LL);
        add_more_eventBtn = findViewById(R.id.add_more_eventBtn);
        radioBtn_Term_condition = findViewById(R.id.radioBtn_Term_condition);

        add_img_video_3_RL = findViewById(R.id.add_img_video_3_RL);
        add_img_video_1_RL = findViewById(R.id.add_img_video_1_RL);
        add_img_video_2_RL = findViewById(R.id.add_img_video_2_RL);
        add_img_video_4_RL = findViewById(R.id.add_img_video_4_RL);

//        if (user.getCoachOrEvent().equalsIgnoreCase("1")) {
//            //event
//        } else if (user.getCoachOrEvent().equalsIgnoreCase("2")) {
//            //Coach
//        }

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

    boolean iffree = false;
    boolean ifpaid = false;

//    String Date = dateTxt.getText().toString();
//    String WhatsappCode = wtspcodeTxt.getText().toString();
//    String MobileNumber = mobile__ET.getText().toString();
//    String SnapChat = snap_ET.getText().toString();
//    String InstaGram = insta_ET.getText().toString();
//    String WebSite = wesite_ET.getText().toString();




    public  void DataGetFromAdapterIntent(){

        wtsapcode=getIntent().getStringExtra("whatsappcountrycode");
        wtsapnumber=getIntent().getStringExtra("whatsappnumber");
        instagram=getIntent().getStringExtra("instagram");
         website=getIntent().getStringExtra("website");
        impcountry=getIntent().getStringExtra("countries");
        creatorcoach = getIntent().getStringExtra("creatorcoach");
        payorfree = getIntent().getStringExtra("payorfree");
        status = getIntent().getStringExtra("status");
        image1 = getIntent().getStringExtra("image1");
        image2 = getIntent().getStringExtra("image2");
        date = getIntent().getStringExtra("date");
        eventid = getIntent().getStringExtra("eventid");
        snapchat = getIntent().getStringExtra("snapchat");
        postedby = getIntent().getStringExtra("postedby");

        if (image1 != null) {
//            Glide.with(Activity_Add_Event_1.this).load(image1).into(img1);
            Picasso.get().load(AppController.base_image_url + image1).into(img1);

        }
        if (image2 != null) {
//            Glide.with(Activity_Add_Event_1.this).load(image1).into(img2);
            Picasso.get().load(AppController.base_image_url + image2).into(img2);

        }
        if (date != null) {
            dateTxt.setText(date);

        }
        if (snapchat != null) {

            snap_ET.setText(snapchat);
        }if (instagram != null) {

            insta_ET.setText(instagram);
        }if (wtsapcode != null) {

            wtspcodeTxt.setText(wtsapcode);
        }if (wtsapnumber != null) {

            mobile__ET.setText(wtsapnumber);
        }if (website != null) {

            wesite_ET.setText(website);
        }if (impcountry != null) {

            country_spinner_Txt.setText(impcountry);
        }
    }



    public void EditAddEventCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//            String id = String.valueOf(user.getId());


            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventdate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
            multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
            multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
            multipartTypedOutput.addPart("countryid[]", new TypedString(data.get(0).getId()));
//            multipartTypedOutput.addPart("payorfree", new TypedString(payorfree));
            multipartTypedOutput.addPart("postedby", new TypedString(user.getName()));
            multipartTypedOutput.addPart("creatorcoach", new TypedString(creatorcoach));
            multipartTypedOutput.addPart("posteddate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("status", new TypedString(status));
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));


//
//            if (iffree) {
//                iffree = true;
//                multipartTypedOutput.addPart("payorfree", new TypedString("1"));
//            }
//
//            if (ifpaid) {
//                ifpaid = true;
//                multipartTypedOutput.addPart("payorfree", new TypedString("2"));
//            }


            try {
                if (ifimg1) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img1.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[]", new TypedFile("image/jpg", resizedImage));
                }
                if (ifimg2) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp1.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img2.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage1 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image2")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[]", new TypedFile("image/jpg", resizedImage1));
                }
                if (ifimg3) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp2.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img3.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage2 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image3")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[]", new TypedFile("image/jpg", resizedImage2));
                }

                if (ifimg4) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp3.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img4.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage3 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image4")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[]", new TypedFile("image/jpg", resizedImage3));
                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
            }


//            progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

            appController.paServices.EditEvent(multipartTypedOutput, new Callback<EditEventRootDM>() {

                @Override

                public void success(EditEventRootDM editEventRootDM, Response response) {
//                    progress.dismiss();
                    if (editEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Helper.showToast(Activity_Add_Event_1.this,editEventRootDM.getOutput().getMessage());
//                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));



                        EventId = editEventRootDM.getOutput().getEventid();

//                        if (post_for_free_nowBtn.callOnClick()) {
//
//                            Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());
//
//                        }   if (pay_now_Btn.callOnClick()) {
//                            Intent intent = new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class);
//                            intent.putExtra("eventid", EventId);
//                            startActivity(intent);
//                        }


                    } else
                        Helper.showToast(Activity_Add_Event_1.this, editEventRootDM.getOutput().getMessage());

                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Activity_Add_Event_1.this, getString(R.string.no_internet_connection));

    }


    public void AddEventByCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//            String id = String.valueOf(user.getId());

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventdate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
            multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
            multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
            multipartTypedOutput.addPart("countryid[]", new TypedString(data.get(0).getId()));
//            multipartTypedOutput.addPart("payorfree", new TypedString("1"));
            multipartTypedOutput.addPart("postedby", new TypedString(user.getName()));
            multipartTypedOutput.addPart("creatorcoach", new TypedString("1"));

//
//            if (iffree) {
//                iffree = true;
//                multipartTypedOutput.addPart("payorfree", new TypedString("1"));
//            }
//
//            if (ifpaid) {
//                ifpaid = true;
//                multipartTypedOutput.addPart("payorfree", new TypedString("2"));
//            }

            try {
                if (ifimg1) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img1.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[]", new TypedFile("image/jpg", resizedImage));
                }
                if (ifimg2) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp1.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img2.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage1 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image2")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[]", new TypedFile("image/jpg", resizedImage1));
                }
                if (ifimg3) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp2.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img3.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage2 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image3")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[]", new TypedFile("image/jpg", resizedImage2));
                }

                if (ifimg4) {
                    File f = new File(Activity_Add_Event_1.this.getCacheDir(), "temp3.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) img4.getDrawable()).getBitmap();
//Convert bitmap to byte array
                    Bitmap bitmap = one;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    File resizedImage3 = new Resizer(Activity_Add_Event_1.this)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image4")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[]", new TypedFile("image/jpg", resizedImage3));
                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
            }


            progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

            appController.paServices.AddEventByCreator(multipartTypedOutput, new Callback<AddEventByCreatorRootDM>() {
                @Override
                public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
                    progress.dismiss();
                    if (addEventByCreatorRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());
//                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));
                        EventId = addEventByCreatorRootDM.getOutput().getEventid();

                        Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                        //                        if (post_for_free_nowBtn.callOnClick()) {
//
//                            Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());
//
//                        }   if (pay_now_Btn.callOnClick()) {
//                            Intent intent = new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class);
//                            intent.putExtra("eventid", EventId);
//                            startActivity(intent);
//                        }


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
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(wtspcountryImg);
                CountryId = data.get(position).getId();

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
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(country_Img);

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
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(country_Img1);
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
                            wtspcodeTxt.setText(data.get(0).getCallingcode());
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(wtspcountryImg);
                            country_spinner_Txt.setText(data.get(0).getTitle());
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img);
                            country_spinner_Txt1.setText(data.get(0).getTitle());
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img1);
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

    @OnClick(R.id.img1)
    public void Image1Clicked() {
        imgClicked = 1;
        OpenImage();
    }

    @OnClick(R.id.img2)
    public void Image1Clicked2() {
        imgClicked = 2;
        OpenImage();
    }

    @OnClick(R.id.img3)
    public void Image1Clicked3() {
        imgClicked = 3;
        OpenImage();
    }

    @OnClick(R.id.img4)
    public void Image1Clicked4() {
        imgClicked = 4;
        OpenImage();
    }

    public void OpenImage() {
        Dexter.withActivity(Activity_Add_Event_1.this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    int imgClicked;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Activity_Add_Event_1.this.getContentResolver(), uri);
                    if (imgClicked == 1) {
                        img1.setImageBitmap(bitmap);
                        ifimg1 = true;
                    } else if (imgClicked == 2) {
                        img2.setImageBitmap(bitmap);
                        ifimg2 = true;

                    } else if (imgClicked == 3) {
                        img3.setImageBitmap(bitmap);
                        ifimg3 = true;

                    } else if (imgClicked == 4) {
                        img4.setImageBitmap(bitmap);
                        ifimg3 = true;
                    }
                    // loading profile image from local cache

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(Activity_Add_Event_1.this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(Activity_Add_Event_1.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    int REQUEST_IMAGE = 999;

    private void launchGalleryIntent() {
        Intent intent = new Intent(Activity_Add_Event_1.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }


    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Add_Event_1.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
//            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    boolean ifimg1 = false;
    boolean ifimg2 = false;
    boolean ifimg3 = false;
    boolean ifimg4 = false;

//
//    @OnClick(R.id.img1)
//    public void Img1() {
//        ifimg1 = true;
//     }
//
//    @OnClick(R.id.img2)
//    public void Img2() {
//        ifimg2 = true;
//
//     }
//
//    @OnClick(R.id.img3)
//    public void Img3() {
//        ifimg3 = true;
//     }
//
//    @OnClick(R.id.img4)
//    public void Img4() {
//        ifimg4 = true;
//     }


//    public void ProfileImg() {
//
//        if (ifimg1) {
//
//            Intent iGallery1 = new Intent(Intent.ACTION_PICK);
//            iGallery1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(iGallery1, IMAGE_PICKER_SELECT1);
//
//        } else if (ifimg2) {
//
//            Intent iGallery2 = new Intent(Intent.ACTION_PICK);
//            iGallery2.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(iGallery2, IMAGE_PICKER_SELECT2);
//
//        } else if (ifimg3) {
//            Intent iGallery3 = new Intent(Intent.ACTION_PICK);
//            iGallery3.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(iGallery3, IMAGE_PICKER_SELECT3);
//
//        } else if (ifimg4) {
//            Intent iGallery4 = new Intent(Intent.ACTION_PICK);
//            iGallery4.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(iGallery4, IMAGE_PICKER_SELECT4);
//        }
//
//
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            if (requestCode == IMAGE_PICKER_SELECT1) {
//                ifimg1 = true;
//                 img1.setImageURI(data.getData());
//
//            } else if (requestCode == IMAGE_PICKER_SELECT2) {
//                ifimg2 = true;
//                 img2.setImageURI(data.getData());
//
//            } else if (requestCode == IMAGE_PICKER_SELECT2) {
//                ifimg3 = true;
//                 img3.setImageURI(data.getData());
//
//            } else if (requestCode == IMAGE_PICKER_SELECT2) {
//                ifimg4 = true;
//                 img4.setImageURI(data.getData());
//
//            }
//        }
//
//
//    }


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

//
//        add_img_video_1_RL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent.setType("image/* video/*");
//
//                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                pickIntent.setType("image/* video/*");
//                startActivityForResult(pickIntent, 3);
//            }
//
//        });
//
//
//        add_img_video_2_RL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/* video/*");
//            }
//        });
//
//        add_img_video_3_RL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/* video/*");
//            }
//        });
//
//        add_img_video_4_RL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/* video/*");
//            }
//        });

}
