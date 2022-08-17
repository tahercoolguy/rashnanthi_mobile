package com.master.design.rashnanthi.Activity;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.TimeZone;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.bumptech.glide.Glide;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
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
import com.master.design.rashnanthi.Fragments.Calender_Fragment;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.master.design.rashnanthi.Utils.CameraUtils;


public class Activity_Add_Event_1 extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;
    Activity context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
    String currentDateandTime;

    private static final int IMAGE_PICKER_SELECT1 = 1;
    private static final int IMAGE_PICKER_SELECT2 = 2;
    private static final int IMAGE_PICKER_SELECT3 = 3;
    private static final int IMAGE_PICKER_SELECT4 = 4;
    String image1, image2, image3, image4, date, eventid, snapchat, instagram, wtsapcode, wtsapnumber, website, impcountry, creatorcoach, payorfree, status, postedby, editimage0id, editimage1id;

    MyEventData myEventData1;
    String CountryId = "1", Free, Paid;

    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
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

//    public  void addvideo(){
//        final MediaController mediacontroller = new MediaController(this);
//        mediacontroller.setAnchorView(vd1);
//
//
//        vd1.setMediaController(mediacontroller);
//        vd1.setVideoURI(Uri.parse(arrayList.get(index)));
//        vd1.requestFocus();
//
//        vd1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                        vd1.setMediaController(mediacontroller);
//                        mediacontroller.setAnchorView(vd1);
//
//                    }
//                });
//            }
//        });
//
//        vd1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
//                if (index++ == arrayList.size()) {
//                    index = 0;
//                    mp.release();
//                    Toast.makeText(getApplicationContext(), "Videos completed", Toast.LENGTH_SHORT).show();
//                } else {
//                    vd1.setVideoURI(Uri.parse(arrayList.get(index)));
//                    vd1.start();
//                }
//
//
//            }
//        });
//
//        vd1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                Log.d("API123", "What " + what + " extra " + extra);
//                return false;
//            }
//        });
//    }


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

        Activity_Add_Event_1.this.finish();

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


//    @BindView(R.id.spinnerCountryBottomRL)
//    RelativeLayout spinnerCountryBottomRL;
//
//boolean spinnerCountryBottom =false;
//    @OnClick(R.id.spinnerCountryBottomRL)
//    public void SpinnerCountryOpenActivity() {
//        spinnerCountryBottom=true;
//        startActivityForResult(new Intent(Activity_Add_Event_1.this, Country_Spinner_Activity.class), 48);
//    }

    String mycountryname;
    String mycountryimg;
    String mycountryid;
    String mycode;

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Intent intent = data;
//        if (data != null) {
//            mycountryname = data.getStringExtra("countryname");
//            mycountryimg = data.getStringExtra("countryimg");
//            mycountryid = data.getStringExtra("countryid");
//            mycode = data.getStringExtra("countrycodee");
//            Picasso.get().load(AppController.base_image_url + mycountryimg).into(mycountryimg);
//            country_spinner_Txt.setText(mycountryname);
//
//        }
//
//
//    }


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


        if(user.getLanguageCode().equalsIgnoreCase("en")){
            your_post_will_beTXt.setText(getString(R.string.your_post_will_be_uploaded_within_24_hours));
        }if(user.getLanguageCode().equalsIgnoreCase("ar")){
            your_post_will_beTXt.setText(getString(R.string.your_post_will_be_uploaded_within_24_hours));

        }

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


//            your_post_will_beTXt.setVisibility(View.VISIBLE);
//            radioBtn_Term_condition.setVisibility(View.VISIBLE);
//            continue_Btn.setVisibility(View.VISIBLE);
//            post_for_free_nowBtn.setVisibility(View.VISIBLE);
//            pay_now_Btn.setVisibility(View.VISIBLE);
//            edit_post_Btn.setVisibility(View.GONE);
//            website_LL.setVisibility(View.VISIBLE);

        }


    }


    boolean ifTermsClicked = false;

//    String Date = dateTxt.getText().toString();
//    String WhatsappCode = wtspcodeTxt.getText().toString();
//    String MobileNumber = mobile__ET.getText().toString();
//    String SnapChat = snap_ET.getText().toString();
//    String InstaGram = insta_ET.getText().toString();
//    String WebSite = wesite_ET.getText().toString();


    public void DataGetFromAdapterIntent() {

        wtsapcode = getIntent().getStringExtra("whatsappcountrycode");
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
        editimage0id = getIntent().getStringExtra("editstoryimgid");
        editimage1id = getIntent().getStringExtra("editeventimgid");

        if (image1 != null) {
//            Glide.with(Activity_Add_Event_1.this).load(image1).into(img1);
            if (image1.contains(".mp4")) {
                vd1.setVisibility(View.VISIBLE);
                img1.setVisibility(View.GONE);
                Uri uri = Uri.parse(image1);
                vd1.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd1.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + image1).into(img1);

            }

        }
        if (image2 != null) {
            if (image2.contains(".mp4")) {
                vd2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);
                Uri uri = Uri.parse(image2);
                vd2.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd2.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + image2).into(img2);

            }
        }
        if (image3 != null) {

            if (image3.contains(".mp4")) {
                vd3.setVisibility(View.VISIBLE);
                img3.setVisibility(View.GONE);
                Uri uri = Uri.parse(image3);
                vd3.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd3.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + image3).into(img3);


            }

        }
        if (image4 != null) {
            if (image4.contains(".mp4")) {
                vd4.setVisibility(View.VISIBLE);
                img4.setVisibility(View.GONE);
                Uri uri = Uri.parse(image4);
                vd4.setVideoURI(Uri.parse(AppController.base_image_url + uri));
                vd4.start();
//                loadUrl(AppController.base_image_url + uri);
            } else {
                Picasso.get().load(AppController.base_image_url + image4).into(img4);


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
        if (wtsapnumber != null) {

            mobile__ET.setText(wtsapnumber);
        }
        if (website != null) {

            wesite_ET.setText(website);
        }
        if (impcountry != null) {

            country_spinner_Txt.setText(impcountry);
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


    public void EditAddEventCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            String id = String.valueOf(user.getId());


            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventdate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));
            multipartTypedOutput.addPart("editstoryimgid[0]", new TypedString(editimage0id));
            multipartTypedOutput.addPart("editeventimgid[0]", new TypedString(editimage1id));
//            multipartTypedOutput.addPart("editstoryimgid[1]", new TypedString(editimage0id));
//            multipartTypedOutput.addPart("editeventimgid[1]", new TypedString(editimage1id));
            multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
            multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
            multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
            multipartTypedOutput.addPart("countryid[0]", new TypedString(CountryId));
            multipartTypedOutput.addPart("countryid[1]", new TypedString(CountryId1));
            multipartTypedOutput.addPart("posteddate", new TypedString(dateTxt.getText().toString()));
            multipartTypedOutput.addPart("postedby", new TypedString(id));
            multipartTypedOutput.addPart("creatorcoach", new TypedString(user.getCreatorcoach()));
            multipartTypedOutput.addPart("payorfree", new TypedString(payorfree));


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
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[0]", new TypedFile("image/jpg", resizedImage));
                }

                if (v1) {
                    File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video1));


                    multipartTypedOutput.addPart("storyphotovideo[0]", new TypedFile("video/mp4", imageFile));
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
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image2")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("image/jpg", resizedImage1));
                }

                if (v2) {
                    File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video2));


                    multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("video/mp4", imageFile));
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
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image3")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("storyphotovideo[1]", new TypedFile("image/jpg", resizedImage2));
                }


                if (v3) {
                    File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video3));


                    multipartTypedOutput.addPart("storyphotovideo[1]", new TypedFile("video/mp4", imageFile));
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
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image4")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("image/jpg", resizedImage3));
                }
                if (v4) {
                    File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video4));


                    multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("video/mp4", imageFile));
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }


            progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

            appController.paServices.EditEvent(multipartTypedOutput, new Callback<EditEventRootDM>() {

                @Override

                public void success(EditEventRootDM editEventRootDM, Response response) {
                    progress.dismiss();
                    if (editEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        //                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));
                        EventId = editEventRootDM.getOutput().getEventid();


                        if (ifterm != false) {

                            Helper.showToast(Activity_Add_Event_1.this, editEventRootDM.getOutput().getMessage());

                        } else {
                            Helper.showToast(Activity_Add_Event_1.this, getString(R.string.kindly_select_terms));

                        }
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
                multipartTypedOutput.addPart("eventdate", new TypedString(dateTxt.getText().toString()));
                multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
                multipartTypedOutput.addPart("whatsapnumber", new TypedString(mobile__ET.getText().toString()));
                multipartTypedOutput.addPart("snapchat", new TypedString(snap_ET.getText().toString()));
                multipartTypedOutput.addPart("instagram", new TypedString(insta_ET.getText().toString()));
                multipartTypedOutput.addPart("website", new TypedString(wesite_ET.getText().toString()));
                multipartTypedOutput.addPart("countryid[0]", new TypedString(CountryId));
                multipartTypedOutput.addPart("countryid[1]", new TypedString(CountryId1));
                multipartTypedOutput.addPart("posteddate", new TypedString(dateTxt.getText().toString()));
                multipartTypedOutput.addPart("postedby", new TypedString(id));
                multipartTypedOutput.addPart("creatorcoach", new TypedString(user.getCreatorcoach()));

                if (iffree != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("1"));
                }
                if (ifpaid != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("2"));
                }

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
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image1")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("storyphotovideo[0]", new TypedFile("image/jpg", resizedImage));
                    }

                    if (v1) {
                        File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video1));


                        multipartTypedOutput.addPart("storyphotovideo[0]", new TypedFile("video/mp4", imageFile));
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
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image2")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("image/jpg", resizedImage1));
                    }

                    if (v2) {
                        File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video2));


                        multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("video/mp4", imageFile));
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
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image3")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("storyphotovideo[1]", new TypedFile("image/jpg", resizedImage2));
                    }


                    if (v3) {
                        File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video3));


                        multipartTypedOutput.addPart("storyphotovideo[1]", new TypedFile("video/mp4", imageFile));
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
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image4")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("image/jpg", resizedImage3));
                    }
                    if (v4) {
                        File imageFile = new File(getRealPathFromUri(Activity_Add_Event_1.this, Video4));


                        multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("video/mp4", imageFile));
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }


                boolean correct = true;
                if (dateTxt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.select_date));
                } else if (wtspcodeTxt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_whatsap_code));
                } else if (mobile__ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_whatsapp));
                } else if (snap_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_snapchat));
                } else if (insta_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_insta));
                } else if (wesite_ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_website));
                } else if (country_spinner_Txt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_main_country));
                } else if (country_spinner_Txt1.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Activity_Add_Event_1.this, getString(R.string.enter_main_country));
                } else if (correct) {

                    progress = dialogUtil.showProgressDialog(Activity_Add_Event_1.this, getString(R.string.please_wait));

                    appController.paServices.AddEventByCreator(multipartTypedOutput, new Callback<AddEventByCreatorRootDM>() {
                        @Override
                        public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
                            progress.dismiss();
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
                                Helper.showToast(Activity_Add_Event_1.this, getString(R.string.the_post_under_review));
                                Activity_Add_Event_1.this.finish();


                            } else
                                Helper.showToast(Activity_Add_Event_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            progress.dismiss();
                            Log.e("error", retrofitError.toString());

                        }
                    });

                }
            } else
                Helper.showToast(Activity_Add_Event_1.this, getString(R.string.no_internet_connection));

        } else {
            Helper.showToast(Activity_Add_Event_1.this, getString(R.string.kindly_select_terms));

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

    boolean wtsapclick=false;

    @OnClick(R.id.wtsapRL)
    public void WhatsappCodeCountry() {
        wtsapclick=true;
        startActivityForResult(new Intent(Activity_Add_Event_1.this, Country_Spinner_Activity.class),48);


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
//
//boolean spinnercountry11 = false;
//    @OnClick(R.id.spinnerCountryBottomRL1)
//    public void SpinnerCountry1() {
//        spinnercountry11=true;
//        startActivityForResult(new Intent(Activity_Add_Event_1.this, Country_Spinner_Activity.class),48);
//
//    }


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
                            Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(wtspcountryImg);
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
                        Helper.showToast(Activity_Add_Event_1.this, getString(R.string.some_netork_happened));
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
        imgClicked = 1;

        OpenImage();
//            Intent intent = new Intent();
//            intent.setType("video/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Video"), SELECT_VIDEO);

//        showPictureDialog();

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

    @OnClick(R.id.vd1)
    public void setVd1() {
        imgClicked = 1;

        OpenImage();
    }

    @OnClick(R.id.vd2)
    public void setVd2() {
        imgClicked = 2;

        OpenImage();
    }

    @OnClick(R.id.vd3)
    public void setVd3() {
        imgClicked = 3;

        OpenImage();
    }

    @OnClick(R.id.vd4)
    public void setVd4() {
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


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select video from gallery",
                "Record video from camera", "Select image from gallery", "Take image from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseVideoFromGallary();
                                break;
                            case 1:
                                takeVideoFromCamera();
                                break;
                            case 2:
                                chooseImageFromGallary();
                                break;
                            case 3:
                                chooseImageFromCamera();
                                break;

                        }
                    }
                });
        pictureDialog.show();
    }

    private static final String VIDEO_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    private int SELECT_PICTURE = 200;
    private int CAMERA_PIC_REQUEST = 300;


    public void chooseVideoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    public void chooseImageFromGallary() {
        // this function is triggered when
        // the Select Image Button is clicked


        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void chooseImageFromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    private void takeVideoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        Log.d("result",""+resultCode);
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == this.RESULT_CANCELED) {
//            Log.d("what","cancle");
//            return;
//        }
//        if (requestCode == GALLERY) {
//            Log.d("what","gale");
//            if (data != null) {
//                Uri contentURI = data.getData();
//
//                String selectedVideoPath = getPath(contentURI);
//                Log.d("path",selectedVideoPath);
//                saveVideoToInternalStorage(selectedVideoPath);
//                vd1.setVideoURI(contentURI);
//                vd1.requestFocus();
//                vd1.start();
//
//            }
//
//        } else if (requestCode == CAMERA) {
//            Uri contentURI = data.getData();
//            String recordedVideoPath = getPath(contentURI);
//            Log.d("frrr",recordedVideoPath);
//            saveVideoToInternalStorage(recordedVideoPath);
//            vd1.setVideoURI(contentURI);
//            vd1.requestFocus();
//            vd1.start();
//        }
//    }

    private void saveVideoToInternalStorage(String filePath) {

        File newfile;

        try {

            File currentFile = new File(filePath);
            File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + VIDEO_DIRECTORY);
            newfile = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".mp4");

            if (!wallpaperDirectory.exists()) {
                wallpaperDirectory.mkdirs();
            }

            if (currentFile.exists()) {

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.v("vii", "Video file saved successfully.");
            } else {
                Log.v("vii", "Video saving failed. Source file missing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
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


            if(wtsapclick==true){
                Picasso.get().load(AppController.base_image_url + mycountryimg).into(wtspcountryImg);
                wtspcodeTxt.setText(mycode);
            }
//            if(spinnerCountryBottom==true){
//
//                    country_spinner_Txt.setText(mycountryname);
//                Picasso.get().load(AppController.base_image_url + mycountryimg).into(country_Img);
//                CountryId =  data.getStringExtra("countryid");
//
//             }if(spinnercountry11==true){
//                country_spinner_Txt1.setText(mycountryname);
//                Picasso.get().load(AppController.base_image_url + mycountryimg).into(country_Img1);
//
//             }

        }
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
                        ifimg4 = true;
                    }
                    // loading profile image from local cache

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        if (requestCode == REQUEST_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getParcelableExtra("path");
//                try {
//
//                    CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
//                    Video = Uri.fromFile(new File(imageStoragePath));
////                    UploadVideo();
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        if (requestCode == IMAGE_VIDEO_ACTIVITY_PICKER) {
            if (data != null) {
                if (data.getStringExtra("mode").equalsIgnoreCase("photo")) {
                    Uri.fromFile(new File(data.getStringExtra("uri")));

                } else {


                    if (imgClicked == 1) {


                        Video1 = Uri.fromFile(new File(data.getStringExtra("uri")));
                        String path = Video1.getPath();
                        CameraUtils.refreshGallery(getApplicationContext(), path);

                        vd1.setVisibility(View.VISIBLE);
                        img1.setVisibility(View.GONE);
                        vd1.setVideoPath(path);
                        // start playing
                        vd1.start();
                        ifimg1 = false;
                        v1 = true;
                    } else if (imgClicked == 2) {

                        Video2 = Uri.fromFile(new File(data.getStringExtra("uri")));
                        String path = Video2.getPath();
                        CameraUtils.refreshGallery(getApplicationContext(), path);
                        vd2.setVisibility(View.VISIBLE);

                        img2.setVisibility(View.GONE);

                        vd2.setVideoPath(path);
                        // start playing
                        vd2.start();
                        ifimg2 = false;
                        v2 = true;

                    } else if (imgClicked == 3) {
                        Video3 = Uri.fromFile(new File(data.getStringExtra("uri")));
                        String path = Video3.getPath();
                        CameraUtils.refreshGallery(getApplicationContext(), path);
                        vd3.setVisibility(View.VISIBLE);

                        img3.setVisibility(View.GONE);

                        vd3.setVideoPath(path);
                        // start playing
                        vd3.start();

                        ifimg3 = false;
                        v3 = true;

                    } else if (imgClicked == 4) {
                        Video4 = Uri.fromFile(new File(data.getStringExtra("uri")));
                        String path = Video4.getPath();
                        CameraUtils.refreshGallery(getApplicationContext(), path);

                        vd4.setVisibility(View.VISIBLE);

                        img4.setVisibility(View.GONE);

                        vd4.setVideoPath(path);
                        // start playing
                        vd4.start();

                        ifimg4 = false;
                        v4 = true;
                    }


//                    UploadVideo();
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
//            Log.d("result",""+resultCode);
//            super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode == this.RESULT_CANCELED) {
//                Log.d("what","cancle");
//                return;
//            }
//            if (requestCode == GALLERY) {
//                Log.d("what","gale");
//                if (data != null) {
//                    Uri contentURI = data.getData();
//                    img1.setVisibility(View.GONE);
//                    cam1.setVisibility(View.GONE);
//                    vd1.setVisibility(View.VISIBLE);
//                    String selectedVideoPath = getPath(contentURI);
//                    Log.d("path",selectedVideoPath);
//                    saveVideoToInternalStorage(selectedVideoPath);
//                    vd1.setVideoURI(contentURI);
//                    vd1.requestFocus();
//                    vd1.start();
//
//                }
//
//            }
//            if (requestCode == CAMERA) {
//                Uri contentURI = data.getData();
//                String recordedVideoPath = getPath(contentURI);
//                Log.d("frrr",recordedVideoPath);
//                img1.setVisibility(View.GONE);
//                cam1.setVisibility(View.GONE);
//                vd1.setVisibility(View.VISIBLE);
//                saveVideoToInternalStorage(recordedVideoPath);
//                vd1.setVideoURI(contentURI);
//                vd1.requestFocus();
//                vd1.start();
//            }


//          if (requestCode == SELECT_PICTURE) {
//            // Get the url of the image from data
//            Uri selectedImageUri = data.getData();
//            if (null != selectedImageUri) {
//                img1.setVisibility(View.VISIBLE);
//                cam1.setVisibility(View.GONE);
//                vd1.setVisibility(View.GONE);
//                // update the preview image in the layout
//                img1.setImageURI(selectedImageUri);
//            }
//        }
//          if (requestCode == CAMERA) {
//            // Get the url of the image from data
//            Uri selectedImageUri = data.getData();
//            if (null != selectedImageUri) {
//                img1.setVisibility(View.GONE);
//                cam1.setVisibility(View.GONE);
//                vd1.setVisibility(View.VISIBLE);
//                // update the preview image in the layout
//                img1.setImageURI(selectedImageUri);
//            }
//        }
//          if (requestCode == CAMERA_PIC_REQUEST) {
//            // Get the url of the image from data
//            Uri selectedImageUri = data.getData();
////            Uri uri = data.getParcelableExtra("path");
//            try
//            {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Activity_Add_Event_1.this.getContentResolver(), selectedImageUri);
//
//                if (null != selectedImageUri) {
//                    img1.setVisibility(View.VISIBLE);
//                    cam1.setVisibility(View.GONE);
//                    vd1.setVisibility(View.GONE);
//                    // update the preview image in the layout
//                    img1.setImageBitmap(bitmap);
//                    ifimg1 = true;
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }

//          if (requestCode == REQUEST_IMAGE_VIDEO) {
////            if (resultCode == Activity.RESULT_OK) {
////                Uri uri = data.getParcelableExtra("path");
////            Uri uri = data.getParcelableExtra("path");
////            vd1.setVideoURI(uri);
//                try {
//                    // You can update this bitmap to your server
//
//                    if (imgClicked == 1) {
//                        img1.setVisibility(View.GONE);
//                        cam1.setVisibility(View.GONE);
//                        vd1.setVisibility(View.VISIBLE);
//                        Uri contentURI = data.getData();
//                        String recordedVideoPath = getPath(contentURI);
//                        Log.d("frrr",recordedVideoPath);
//                        img1.setVisibility(View.GONE);
//                        cam1.setVisibility(View.GONE);
//                        vd1.setVisibility(View.VISIBLE);
//                        saveVideoToInternalStorage(recordedVideoPath);
//                        vd1.setVideoURI(contentURI);
//                        vd1.requestFocus();
//                        vd1.start();
//                    }
//                } catch (Exception e) {
//                    Log.e("Exception",e.toString());
//                }
////            }
//
//        }
//          if (requestCode == GALLERY) {
//            Uri mVideoURI = data.getData();
//            vd1.setVideoURI(mVideoURI);
//            img1.setVisibility(View.GONE);
//            cam1.setVisibility(View.GONE);
//            vd1.setVisibility(View.VISIBLE);
//            MediaController mediaController = new MediaController(this);
//            mediaController.setAnchorView(vd1);
//            vd1.setMediaController(mediaController);
//
//            vd1.start();
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(Activity_Add_Event_1.this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onTakeCameraSelectedVideo() {
//                launchCameraIntent();
                launchCameraIntentVideo();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        }, true);
    }

    private void launchCameraIntent() {
//        Intent intent = new Intent(Activity_Add_Event_1.this, ImagePickerActivity.class);
//        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
//
//        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
//
//        // setting maximum bitmap width and height
//        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
//
//        startActivityForResult(intent, REQUEST_IMAGE);
//        Intent intent=new Intent(Activity_Add_Event_1.this,CameraHandling.class);
//        intent.putExtra("mode","photo");
//        startActivityForResult(intent,IMAGE_VIDEO_ACTIVITY_PICKER);


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


    private void launchCameraIntentVideo() {
//        Intent intent = new Intent(Activity_Add_Event_1.this, ImagePickerActivity.class);
//        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE_VIDEO);
//
//        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
//
//        // setting maximum bitmap width and height
//        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
//
//        startActivityForResult(intent, REQUEST_IMAGE_VIDEO);

        Intent intent = new Intent(Activity_Add_Event_1.this, CameraHandling.class);
        intent.putExtra("mode", "video");
        startActivityForResult(intent, IMAGE_VIDEO_ACTIVITY_PICKER);
    }

    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;


    int REQUEST_IMAGE = 999;
    int REQUEST_IMAGE_VIDEO = 998;

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