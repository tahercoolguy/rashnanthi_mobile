package com.master.design.rashnanthi.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.AddEventByCreatorRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.EditEventRootDM;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.CameraUtils;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


public class Activity_New_Add_Event extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;
    Activity context;
    Context context1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
    String currentDateandTime;


    String image1, mobile, image2, image3, image4, date, eventid, snapchat,
            editimage0, editimage1, editimage2, editimage3, editimage4, instagram, wtsapcode, contactCC, wtsapnumber, website, impcountry, creatorcoach, payorfree, status, postedby, editimage0id, editimage1id;

    MyEventData myEventData1;
    String CountryId = "1", Free, Paid;

     int index = 0;

    @NotEmpty
    @BindView(R.id.dateTxt)
    TextView dateTxt;

    @NotEmpty
    @BindView(R.id.dateee)
    TextView Dateee;

    @NotEmpty
    @BindView(R.id.montheee)
    TextView Montheee;

    @NotEmpty
    @BindView(R.id.yearrr)
    TextView Yearrr;

    @NotEmpty
    @BindView(R.id.dateRL)
    RelativeLayout dateRL;

    @NotEmpty
    @BindView(R.id.add_event_pay_back)
    ImageView add_event_pay_back;


    @BindView(R.id.vd1)
    VideoView vd1;

    @NotEmpty
    @BindView(R.id.wtspcountryImg)
    ImageView wtspcountryImg;

    @NotEmpty
    @BindView(R.id.contactcountryImg)
    ImageView contactcountryImg;

    @NotEmpty
    @BindView(R.id.wtspcodeTxt)
    TextView wtspcodeTxt;

    //    @NotEmpty
    @BindView(R.id.contactCountrycodeTxt)
    TextView contactCountrycodeTxt;

    @NotEmpty
    @BindView(R.id.wtsapRL)
    RelativeLayout wtsapRL;

    @NotEmpty
    @BindView(R.id.contactRL)
    RelativeLayout contactRL;

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
    @BindView(R.id.contact__ET)
    EditText contact__ET;

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
    @BindView(R.id.edit_post_Btn)
    Button edit_post_Btn;

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
    @BindView(R.id.termsRB)
    RadioButton termsRB;

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
    private static String imageStoragePath;
    String EventId;
    boolean iffree = false;
    boolean ifpaid = false;


    @OnClick(R.id.pay_now_Btn)
    public void PayNow() {
        ifpaid = true;
        iffree = false;
        if (status != null) {

            EditAddEventCreatorAPI();

        } else {

            AddEventByCreatorAPI();
        }


//        startActivity(new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.add_event_pay_back)
    public void back() {

        Activity_New_Add_Event.this.finish();

    }

    boolean ifterm = false;

    @OnClick(R.id.termsRB)
    public void Term() {
        ifterm = true;

    }

    @OnClick(R.id.continue_Btn)
    public void Continue() {
        iffree = true;
        if (status != null) {
            continue_Btn.setText(getString(R.string.edit_event));
            EditAddEventCreatorAPI();

        } else {

            continue_Btn.setText(getString(R.string.continuee));
            AddEventByCreatorAPI();
        }
//        startActivity(new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class));
    }

    @OnClick(R.id.edit_post_Btn)
    public void Edit() {
        EditAddEventCreatorAPI();

    }

    @OnClick(R.id.post_for_free_nowBtn)
    public void PostForFreeNow() {
        iffree = true;
        ifpaid = false;
        if (status != null) {
            EditAddEventCreatorAPI();
        } else {
            AddEventByCreatorAPI();
        }
    }



    String mycountryname;
    String mycountryimg;
    String mycountryid;
    String mycode;


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
        context = Activity_New_Add_Event.this;
        dialogUtil = new DialogUtil();
        appController = (AppController) context.getApplicationContext();
        myEventData1 = new MyEventData();

        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Activity_New_Add_Event.this);

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


        if (user.getLanguageCode().equalsIgnoreCase("en")) {
            your_post_will_beTXt.setText(getString(R.string.your_post_will_be_uploaded_within_24_hours));
        }
        if (user.getLanguageCode().equalsIgnoreCase("ar")) {
            your_post_will_beTXt.setText(getString(R.string.your_post_will_be_uploaded_within_24_hours));

        }


        add_more_eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifAddMoreEventClicked1 = true;
                NotificationVisibilityFunction1();
            }
        });

        ad_more_eventtLL.setVisibility(View.GONE);
        your_post_will_beTXt.setVisibility(View.GONE);
        if (status == null) {
            continue_Btn.setVisibility(View.GONE);

        }
        NotificationVisibilityFunction1();
    }


    boolean ifAddMoreEventClicked1 = false;

    public void NotificationVisibilityFunction1() {

        if (ifAddMoreEventClicked1) {

            ad_more_eventtLL.setVisibility(View.VISIBLE);
            add_more_eventBtn.setVisibility(View.VISIBLE);

            if (status != null) {
                pay_now_Btn.setVisibility(View.GONE);
                post_for_free_nowBtn.setVisibility(View.GONE);
                edit_post_Btn.setVisibility(View.VISIBLE);
            }


        }


    }


    String id0, id1;

    public void DataGetFromAdapterIntent() {

        wtsapcode = getIntent().getStringExtra("whatsappcountrycode");
        contactCC = getIntent().getStringExtra("countrycode");

        wtsapnumber = getIntent().getStringExtra("whatsappnumber");
        instagram = getIntent().getStringExtra("instagram");
        website = getIntent().getStringExtra("website");
        impcountry = getIntent().getStringExtra("countries");
        creatorcoach = getIntent().getStringExtra("creatorcoach");
        payorfree = getIntent().getStringExtra("payorfree");
        status = getIntent().getStringExtra("status");
        image1 = getIntent().getStringExtra("image1");
        image2 = getIntent().getStringExtra("image2");
        image3 = getIntent().getStringExtra("image3");
        image4 = getIntent().getStringExtra("image4");
        date = getIntent().getStringExtra("date");
        eventid = getIntent().getStringExtra("eventid");
        snapchat = getIntent().getStringExtra("snapchat");
        postedby = getIntent().getStringExtra("postedby");
        mobile = getIntent().getStringExtra("mobile");
        id0 = getIntent().getStringExtra("id0");
        id1 = getIntent().getStringExtra("id1");

        editimage0 = getIntent().getStringExtra("editstoryimg0");
        editimage1 = getIntent().getStringExtra("editeventimg1");
        editimage2 = getIntent().getStringExtra("editeventimg2");
        editimage3 = getIntent().getStringExtra("editeventimg3");
        editimage4 = getIntent().getStringExtra("editeventimg4");

        if (editimage0 != null) {
//            Glide.with(Activity_Add_Event_1.this).load(image1).into(img1);
            if (editimage0.contains(".mp4")) {
                vd1.setVisibility(View.VISIBLE);
                img1.setVisibility(View.GONE);
                Uri uri = Uri.parse(editimage0);
                vd1.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd1.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + editimage0).into(img1);

            }

        }
        if (editimage1 != null) {
            if (editimage1.contains(".mp4")) {
                vd2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);
                Uri uri = Uri.parse(editimage1);
                vd2.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd2.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + editimage1).into(img2);

            }
        }
        if (editimage2 != null) {

            if (editimage2.contains(".mp4")) {
                vd3.setVisibility(View.VISIBLE);
                img3.setVisibility(View.GONE);
                Uri uri = Uri.parse(editimage2);
                vd3.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd3.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + editimage2).into(img3);


            }

        }
        if (editimage3 != null) {
            if (editimage3.contains(".mp4")) {
                vd4.setVisibility(View.VISIBLE);
                img4.setVisibility(View.GONE);
                Uri uri = Uri.parse(editimage3);
                vd4.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd4.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + editimage3).into(img4);


            }

        }
        if (date != null) {
            dateTxt.setText(date);
            String dob = date;

            int mth = 0, dd = 0, yer = 0;

            try {

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date d = sdf.parse(dob);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                mth = cal.get(Calendar.MONTH);
                dd = cal.get(Calendar.DATE);
                yer = cal.get(Calendar.YEAR);

                Dateee.setText(String.valueOf(dd));
                Montheee.setText(String.valueOf(mth));
                Yearrr.setText(String.valueOf(yer));
                dateTxt.setText(yer + mth + dd);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        if (snapchat != null) {

            snap_ET.setText(snapchat);
        }
        if (instagram != null) {

            insta_ET.setText(instagram);
        }
        if (wtsapcode != null) {

            wtspcodeTxt.setText(wtsapcode);
        }
        if (contactCountrycodeTxt != null) {

            contactCountrycodeTxt.setText(contactCC);
        }
        if (wtsapnumber != null) {

            mobile__ET.setText(wtsapnumber);
        }

        if (mobile != null) {

            contact__ET.setText(mobile);
        }
        if (website != null) {

            wesite_ET.setText(website);
        }
        if (impcountry != null) {

//            country_spinner_Txt.setText(impcountry);
        }
        if (status != null) {
            pay_now_Btn.setVisibility(View.GONE);
//            termsRB.setVisibility(View.GONE);
            edit_post_Btn.setVisibility(View.VISIBLE);

//            your_post_will_beTXt.setVisibility(View.GONE);
            post_for_free_nowBtn.setVisibility(View.GONE);
        }
//        } else {
//            continue_Btn.setText(getString(R.string.add_event));
//            pay_now_Btn.setVisibility(View.VISIBLE);
//            post_for_free_nowBtn.setVisibility(View.VISIBLE);
//            continue_Btn.setVisibility(View.GONE);
//
//        }
    }

    boolean idOne, idTwo = false;

    public void EditAddEventCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            String id = String.valueOf(user.getId());
            String newdate = dateTxt.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(newdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat finalDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String finalDate = finalDateFormat.format(date);

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventdate", new TypedString(finalDate));
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));
            if (id0 != null) {
                if (idOne) {
                    multipartTypedOutput.addPart("editstoryimgid[1]", new TypedString(id0));
                }

            }
            if (id1 != null) {
                if (idTwo) {
                    multipartTypedOutput.addPart("editeventimgid[2]", new TypedString(id1));
                }

            }

//            multipartTypedOutput.addPart("editstoryimgid[1]", new TypedString(editimage0id));
//            multipartTypedOutput.addPart("editeventimgid[1]", new TypedString(editimage1id));
            multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
            multipartTypedOutput.addPart("mobile", new TypedString(contact__ET.getText().toString()));

            multipartTypedOutput.addPart("countrycode", new TypedString(contactCountrycodeTxt.getText().toString()));

            multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
            multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
            multipartTypedOutput.addPart("countryid[0]", new TypedString(CountryId));
            multipartTypedOutput.addPart("countryid[1]", new TypedString(CountryId1));
            multipartTypedOutput.addPart("posteddate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("postedby", new TypedString(id));
            multipartTypedOutput.addPart("creatorcoach", new TypedString(user.getCreatorcoach()));
            multipartTypedOutput.addPart("payorfree", new TypedString(payorfree));





//            progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

            appController.paServices.EditEvent(multipartTypedOutput, new Callback<EditEventRootDM>() {

                @Override

                public void success(EditEventRootDM editEventRootDM, Response response) {
//                    progress.dismiss();
                    if (editEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        //                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));
                        EventId = editEventRootDM.getOutput().getEventid();


                        if (ifterm != false) {

                            Helper.showToast(Activity_New_Add_Event.this, editEventRootDM.getOutput().getMessage());
                            Activity_New_Add_Event.this.finish();


                        } else {
                            Helper.showToast(Activity_New_Add_Event.this, getString(R.string.kindly_select_terms));

                        }
                    } else
                        Helper.showToast(Activity_New_Add_Event.this, editEventRootDM.getOutput().getMessage());

                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Activity_New_Add_Event.this, getString(R.string.no_internet_connection));

    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
        }
    }

    public void AddEventByCreatorAPI() {
        if (ifterm != false) {


            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                String id = String.valueOf(user.getId());

                MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();

                String newdate = dateTxt.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(newdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat finalDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String finalDate = finalDateFormat.format(date);
                multipartTypedOutput.addPart("eventdate", new TypedString(finalDate));
                multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
                multipartTypedOutput.addPart("countrycode", new TypedString(contactCountrycodeTxt.getText().toString()));
                multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
                multipartTypedOutput.addPart("mobile", new TypedString(contact__ET.getText().toString()));
                multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
                multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
                multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
                multipartTypedOutput.addPart("countryid[0]", new TypedString(CountryId));
                multipartTypedOutput.addPart("countryid[1]", new TypedString(CountryId1));
                multipartTypedOutput.addPart("posteddate", new TypedString(finalDate));
                multipartTypedOutput.addPart("postedby", new TypedString(id));
                multipartTypedOutput.addPart("creatorcoach", new TypedString(user.getCreatorcoach()));

                if (iffree != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("1"));
                }
                if (ifpaid != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("2"));
                }




                boolean correct = true;
                if (dateTxt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.select_date));
                } else if (wtspcodeTxt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_whatsap_code));
                } else if (mobile__ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_whatsapp));
                } else if (snap_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_snapchat));
                } else if (insta_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_insta));
                } else if (wesite_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_website));
                } else if (country_spinner_Txt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_main_country));
                } else if (country_spinner_Txt1.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_New_Add_Event.this, getString(R.string.enter_main_country));
                } else if (correct) {

//                    progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

                    appController.paServices.AddEventByCreator(multipartTypedOutput, new Callback<AddEventByCreatorRootDM>() {
                        @Override
                        public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
//                            progress.dismiss();
                            if (addEventByCreatorRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {


                                //                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));
                                EventId = addEventByCreatorRootDM.getOutput().getEventid();


//                                if (iffree != false) {
//                                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.the_post_under_review));
//                                    Activity_Add_Event_1.this.finish();
//
//                                }
//                                if (ifpaid != false) {
//                                    Intent intent = new Intent(Activity_Add_Event_1.this, Add_Event_Pay_Now.class);
//                                    intent.putExtra("eventid", EventId);
//                                    startActivity(intent);
//                                }
                                Helper.showToast(Activity_New_Add_Event.this, getString(R.string.the_post_under_review));
                                Activity_New_Add_Event.this.finish();


                            } else
                                Helper.showToast(Activity_New_Add_Event.this, addEventByCreatorRootDM.getOutput().getMessage());

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
//                            progress.dismiss();
                            Log.e("error", retrofitError.toString());

                        }
                    });

                }
            } else
                Helper.showToast(Activity_New_Add_Event.this, getString(R.string.no_internet_connection));

        } else {
            Helper.showToast(Activity_New_Add_Event.this, getString(R.string.kindly_select_terms));

        }
    }


    int mYear, mMonth, mDay, expiryDay;

    @OnClick(R.id.dateRL)
    public void date() {
//        CalenderDataPicker();


    }

    @OnClick(R.id.datell)
    public void dateLL() {
//        CalenderDataPicker();
//        datePickerCalender();
        datepick();
    }

    public void datepick() {
        new SingleDateAndTimePickerDialog.Builder(this)
                .bottomSheet()
                .curved()
                .displayMinutes(false)
                .displayHours(false)
                .displayDays(false)
                .displayMonth(true)
                .mainColor(getColor(R.color.black))
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        String inputPattern = "yyyy-MM-dd";
                        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
                        String inputPattern2 = "yyyy-MMM-dd";
                        SimpleDateFormat inputFormat2 = new SimpleDateFormat(inputPattern2);
                        try {
                            String str = inputFormat.format(date);
                            String str1 = inputFormat2.format(date);
                            dateTxt.setText(str);

                            String Month = "MMM";
                            inputFormat2 = new SimpleDateFormat(Month);
                            String MonthText = inputFormat2.format(date);
                            Montheee.setText(MonthText);

                            String Year = "yyyy";
                            inputFormat = new SimpleDateFormat(Year);
                            String YearText = inputFormat.format(date);
                            Yearrr.setText(YearText);

                            String Day = "dd";
                            inputFormat = new SimpleDateFormat(Day);
                            String DateText = inputFormat.format(date);
                            Dateee.setText(DateText);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })

                .displayYears(true)
                .displayDaysOfMonth(true)
                .display();

    }


//    public void datePickerCalender() {
//        currentDateandTime = sdf.format(Calendar.getInstance().getTime());
//        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(Activity_Add_Event_1.this, new DatePickerPopWin.OnDatePickedListener() {
//
//
//            @Override
//            public void onDatePickCompleted(int year, int monthOfYear, int dayOfMonth, String dateDesc) {
//
////                Toast.makeText(Activity_Add_Event_1.this, dateDesc, Toast.LENGTH_SHORT).show();
//
////                if ((monthOfYear + 1) <= 9) {
////                    dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
////                    if (dayOfMonth <= 9)
////                        dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
////
////
////                } else {
////                    dateTxt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
////                    if (dayOfMonth <= 9)
////                        dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
////                }
//
//                String dateeee = String.valueOf(dayOfMonth);
//                String montheeee = String.valueOf(monthOfYear);
//                String yearrrrr = String.valueOf(year);
//
//
//
//
//                Dateee.setText(dateeee);
//                Montheee.setText(montheeee);
//                Yearrr.setText(yearrrrr);
//                dateTxt.setText(dateDesc);
//            }
//        }).textConfirm(getString(R.string.done)) //text of confirm button
//                .textCancel(getString(R.string.cancel)) //text of cancel button
//                .btnTextSize(14) // button text size
//                .viewTextSize(1000) // pick view text size
//                .colorCancel(Color.parseColor("#CE010E")) //color of cancel button
//                .colorConfirm(Color.parseColor("#CE010E"))//color of confirm button
//                .minYear(2022) //min year in loop
//                .maxYear(2550) // max year in loop
//                .showDayMonthYear(false) // shows like dd mm yyyy (default is false)
//                .dateChose(currentDateandTime) // date chose when init popwindow
//                .build();
//
//
//        pickerPopWin.showPopWin(Activity_Add_Event_1.this);
//    }


//    public void CalenderDataPicker() {
//        // Get Current Date
//        final java.util.Calendar c = java.util.Calendar.getInstance();
//        mYear = c.get(java.util.Calendar.YEAR);
//        mMonth = c.get(java.util.Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(Activity_Add_Event_1.this,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//
//                        if ((monthOfYear + 1) <= 9) {
//                            dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
//                            if (dayOfMonth <= 9)
//                                dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
//
//                        } else {
//                            dateTxt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
//                            if (dayOfMonth <= 9)
//                                dateTxt.setText(year + "-0" + (monthOfYear + 1) + "-" + "0" + dayOfMonth);
//                        }
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        datePickerDialog.show();
//
//    }

//    public String parseDateToddMMyyyy(String time) {
//        String inputPattern = "dd-MM-yyyy";
//        String outputPattern = "dd-MMM-yyyy";
//        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
//
//        Date date = null;
//        String str = null;
//
//        try {
//            date = inputFormat.parse(time);
//            str = outputFormat.format(date);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }


    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();

//    @OnClick(R.id.wtsapRL)
//    public void WhatsappCodeCountry() {
//
//        bottomForAll = new BottomForAll();
//        bottomForAll.arrayList = approvalOne;
//
//        bottomForAll.setResponseListener(new ResponseListener() {
//            @Override
//            public void response(int position, Object object) {
//
//                wtspcodeTxt.setText(data.get(position).getCallingcode());
////                wtspcountryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));
//                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(wtspcountryImg);
////                CountryId = data.get(position).getId();
//
////                AreaID = data.get(selected).getId();
////                for (CountryData s:data
////                ) {
////                    if(s.getCallingcode().equals((String) object))
////                        AreaID = s.getId();
////                }
//
//
//            }
//        });
//
//
//        bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");
//    }

    boolean wtsapclick = false;
    boolean contactClick = false;

    @OnClick(R.id.wtsapRL)
    public void WhatsappCodeCountry() {
        wtsapclick = true;
        startActivityForResult(new Intent(Activity_New_Add_Event.this, Country_Spinner_Activity.class), 48);


    }

    @OnClick(R.id.contactRL)
    public void ContactCodeCountry() {
        contactClick = true;
        startActivityForResult(new Intent(Activity_New_Add_Event.this, Country_Spinner_Activity.class), 48);


    }


    @OnClick(R.id.spinnerCountryBottomRL)
    public void Spinner__Country() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                if (user.getLanguageCode().equalsIgnoreCase("en")) {
                    country_spinner_Txt.setText(data.get(position).getTitle());

                } else {
                    country_spinner_Txt.setText(data.get(position).getTitlear());

                }
//                country_Img.setImageResource(Integer.parseInt(data.get(position).getImage()));
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(country_Img);
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



    String CountryId1 = "1";

    @OnClick(R.id.spinnerCountryBottomRL1)
    public void SpinnerCountry1() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                if (user.getLanguageCode().equalsIgnoreCase("en")) {
                    country_spinner_Txt1.setText(data.get(position).getTitle());

                } else {
                    country_spinner_Txt1.setText(data.get(position).getTitlear());

                }
                CountryId1 = data.get(position).getId();

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
                            contactCountrycodeTxt.setText(data.get(0).getCallingcode());

                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(wtspcountryImg);
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(contactcountryImg);

                            if (user.getLanguageCode().equalsIgnoreCase("en")) {
                                country_spinner_Txt.setText(data.get(0).getTitle());

                            } else {
                                country_spinner_Txt.setText(data.get(0).getTitlear());

                            }
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img);
                            if (user.getLanguageCode().equalsIgnoreCase("en")) {
                                country_spinner_Txt1.setText(data.get(0).getTitle());

                            } else {
                                country_spinner_Txt1.setText(data.get(0).getTitlear());

                            }
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img1);
                        }
                    } else
                        Helper.showToast(Activity_New_Add_Event.this, getString(R.string.some_netork_happened));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
    }

    private static final int SELECT_VIDEO = 2;

    @OnClick(R.id.img1)
    public void Image1Clicked() {


    }


    @OnClick(R.id.img2)
    public void Image1Clicked2() {

    }

    @OnClick(R.id.img3)
    public void Image1Clicked3() {

    }

    @OnClick(R.id.img4)
    public void Image1Clicked4() {

    }

    @OnClick(R.id.vd1)
    public void setVd1() {

    }

    @OnClick(R.id.vd2)
    public void setVd2() {

    }

    @OnClick(R.id.vd3)
    public void setVd3() {

    }

    @OnClick(R.id.vd4)
    public void setVd4() {

    }









    int imgClicked;

    @BindView(R.id.vd2)
    VideoView vd2;
    @BindView(R.id.vd3)
    VideoView vd3;
    @BindView(R.id.vd4)
    VideoView vd4;

    @BindView(R.id.cam1)
    ImageView cam1;


    Uri Video1, Video2, Video3, Video4;


    boolean v1 = false, v2 = false, v3 = false, v4 = false;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = data;
        if (data != null) {
            mycountryname = data.getStringExtra("countryname");
            mycountryimg = data.getStringExtra("countryimg");
            mycountryid = data.getStringExtra("countryid");
            mycode = data.getStringExtra("countrycodee");
//            Picasso.get().load(AppController.base_image_url + mycountryimg).into(mycountryimg);
//            country_spinner_Txt.setText(mycountryname);


            if (wtsapclick == true) {
                Picasso.get().load(AppController.base_image_url + mycountryimg).into(wtspcountryImg);
                wtspcodeTxt.setText(mycode);
            }

            if (contactClick == true) {
                Picasso.get().load(AppController.base_image_url + mycountryimg).into(contactcountryImg);
                contactCountrycodeTxt.setText(mycode);
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
     }
}