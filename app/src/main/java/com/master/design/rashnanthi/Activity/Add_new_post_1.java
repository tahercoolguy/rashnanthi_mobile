package com.master.design.rashnanthi.Activity;

import static com.master.design.rashnanthi.Activity.Activity_Add_Event_1.getRealPathFromUri;

import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

public class Add_new_post_1 extends AppCompatActivity {


    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;
    String image1, image2,editimage0id,editimage1id, image3, image4, date, eventid, snapchat, instagram, wtsapcode, wtsapnumber, website, impcountry, creatorcoach, payorfree, status, postedby;

    @NotEmpty
    @BindView(R.id.add_new_post_pay_back)
    ImageView Back;

    @NotEmpty
    @BindView(R.id.mobileET)
    EditText mobileET;


    @NotEmpty
    @BindView(R.id.wtsapMobile__ET)
    EditText wtsapMobile__ET;

    @NotEmpty
    @BindView(R.id.snapET)
    EditText snapET;

    @NotEmpty
    @BindView(R.id.termsRB)
    RadioButton termsRB;


    @NotEmpty
    @BindView(R.id.instaET)
    EditText instaET;

    @NotEmpty
    @BindView(R.id.post_for_free_nowBtn)
    TextView post_for_free_nowBtn;

    @NotEmpty
    @BindView(R.id.pay_now_Btn)
    Button pay_now_Btn;

    @NotEmpty
    @BindView(R.id.img4)
    ImageView img4;

    @NotEmpty
    @BindView(R.id.img3)
    ImageView img3;

    @NotEmpty
    @BindView(R.id.img1)
    ImageView img1;

    @NotEmpty
    @BindView(R.id.img2)
    ImageView img2;

    @NotEmpty
    @BindView(R.id.web_RL)
    RelativeLayout web_RL;

    @NotEmpty
    @BindView(R.id.webET)
    EditText webET;


    @OnClick(R.id.add_new_post_pay_back)
    public void Back() {
        Add_new_post_1.this.finish();
    }

    boolean ifpay, iffree = false;

    @BindView(R.id.vd1)
    VideoView vd1;
    @BindView(R.id.vd2)
    VideoView vd2;
    @BindView(R.id.vd3)
    VideoView vd3;
    @BindView(R.id.vd4)
    VideoView vd4;


    Uri Video1, Video2, Video3, Video4;


    boolean v1 = false, v2 = false, v3 = false, v4 = false;
    String CountryId = "1";

    @OnClick(R.id.pay_now_Btn)
    public void PayNow() {
        ifpay = true;
        iffree = false;
        AddNewPostByCoach();
    }

    @OnClick(R.id.post_for_free_nowBtn)
    public void PostForFreeNow() {
        iffree = true;
        ifpay = false;
        AddNewPostByCoach();
    }


    RadioButton term_conditionRB;
    //    Button pay_now_Btn, continue_Btn;
    EditText post_for_free_nowET, add_more_eventET;
    TextView you_will_be_uploaded_withon_24_hours;
    private ArrayList<Country_NameDM> countryNameDMS;
    private ArrayList<Country_CodeDM> country_codeDMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post_1);

        ButterKnife.bind(this);


        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Add_new_post_1.this);
        Binding();
        DataGetFromAdapterIntent();

//        pay_now_Btn = findViewById(R.id.pay_now_Btn);
//
//
//        pay_now_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              AddNewPostByCoach();
//              finish();
//            }
//        });


    }

    @NotEmpty
    @BindView(R.id.wtsapRL)
    RelativeLayout wtsapRL;

    @NotEmpty
    @BindView(R.id.editpostnowBtn)
    Button editpostnowBtn;

    @BindView(R.id.country_spinnerET)
    TextView country_spinnerET;

    @BindView(R.id.countryImg)
    ImageView countryImg;

    @BindView(R.id.youpostEditTxt)
    TextView youpostEditTxt;


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
            Picasso.get().load(AppController.base_image_url + image1).into(img1);

        }
        if (image2 != null) {
            Picasso.get().load(AppController.base_image_url + image2).into(img2);

        }
        if (image3 != null) {
            Picasso.get().load(AppController.base_image_url + image3).into(img3);

        }
        if (image4 != null) {
            Picasso.get().load(AppController.base_image_url + image4).into(img4);

        }

        if (snapchat != null) {

            snapET.setText(snapchat);
        }
        if (instagram != null) {

            instaET.setText(instagram);
        }
        if (wtsapcode != null) {

            country_spinnerET.setText(wtsapcode);
            wtspcodeTxt.setText(wtsapcode);
        }
        if (wtsapnumber != null) {

            wtsapMobile__ET.setText(wtsapnumber);
            mobileET.setText(wtsapnumber);
        }
//        if (website != null) {
//
//            wesite_ET.setText(website);
//        }
        if (impcountry != null) {

            country_spinner_Txt.setText(impcountry);
        }
        if (status != null) {
            youpostEditTxt.setText("Your Post will be edited within 24 hours");
            editpostnowBtn.setVisibility(View.VISIBLE);
            pay_now_Btn.setVisibility(View.GONE);
            post_for_free_nowBtn.setVisibility(View.GONE);
        } else {
            youpostEditTxt.setText("Your Post will be uploaded within 24 hours");
            pay_now_Btn.setVisibility(View.VISIBLE);
            post_for_free_nowBtn.setVisibility(View.VISIBLE);
            editpostnowBtn.setVisibility(View.GONE);

        }
    }

    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();

    @NotEmpty
    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;

    @NotEmpty
    @BindView(R.id.country_Img)
    ImageView country_Img;


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


        bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");
    }

    @NotEmpty
    @BindView(R.id.wtspcodeTxt)
    TextView wtspcodeTxt;

    String EventId;

    @NotEmpty
    @BindView(R.id.wtspcountryImg)
    ImageView wtspcountryImg;
    boolean term = false;

    @OnClick(R.id.termsRB)
    public void Terms() {
        term = true;
    }

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
//                CountryId = data.get(position).getId();

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
                            if (approvalOne.get(0).getId().equalsIgnoreCase("1")) {
                                country_spinnerET.setText(data.get(0).getCallingcode());
                                wtspcodeTxt.setText(data.get(0).getCallingcode());
                                country_spinner_Txt.setText(data.get(0).getTitle());
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(countryImg);
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img);
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(wtspcountryImg);
                            }
                        }
                    } else
                        Helper.showToast(Add_new_post_1.this, getString(R.string.something_wrong));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        } else
            Helper.showToast(Add_new_post_1.this, getString(R.string.no_internet_connection));
    }


    public void AddNewPostByCoach() {
        if (term != false) {
            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                String id = String.valueOf(user.getId());


                MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();

//            multipartTypedOutput.addPart("eventdate", new TypedString("2022-06-06"));
                multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
                multipartTypedOutput.addPart("whatsapnumber", new TypedString(wtsapMobile__ET.getText().toString()));
                multipartTypedOutput.addPart("snapchat", new TypedString(snapET.getText().toString()));
                multipartTypedOutput.addPart("instagram", new TypedString(instaET.getText().toString()));
                multipartTypedOutput.addPart("website", new TypedString(webET.getText().toString()));
//            multipartTypedOutput.addPart("countryid[]", new TypedString(data.get(0).getId()));
                multipartTypedOutput.addPart("countryid[]", new TypedString(CountryId));

                multipartTypedOutput.addPart("postedby", new TypedString(id));
                multipartTypedOutput.addPart("creatorcoach", new TypedString("2"));

                if (iffree != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("1"));

                }
                if (ifpay != false) {
                    multipartTypedOutput.addPart("payorfree", new TypedString("2"));
                }

                try {
                    if (ifimg1) {
                        File f = new File(Add_new_post_1.this.getCacheDir(), "temp.jpg");
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
                        File resizedImage = new Resizer(Add_new_post_1.this)
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image1")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("image/jpg", resizedImage));
                    }

                    if (v1) {
                        File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video1));


                        multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("video/mp4", imageFile));
                    }

                    if (ifimg2) {
                        File f = new File(Add_new_post_1.this.getCacheDir(), "temp1.jpg");
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
                        File resizedImage1 = new Resizer(Add_new_post_1.this)
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image2")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("image/jpg", resizedImage1));
                    }

                    if (v2) {
                        File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video2));


                        multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("video/mp4", imageFile));
                    }

                    if (ifimg3) {
                        File f = new File(Add_new_post_1.this.getCacheDir(), "temp2.jpg");
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
                        File resizedImage2 = new Resizer(Add_new_post_1.this)
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image3")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[2]", new TypedFile("image/jpg", resizedImage2));
                    }

                    if (v3) {
                        File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video3));


                        multipartTypedOutput.addPart("eventphotovideo[2]", new TypedFile("video/mp4", imageFile));
                    }
                    if (ifimg4) {
                        File f = new File(Add_new_post_1.this.getCacheDir(), "temp3.jpg");
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
                        File resizedImage3 = new Resizer(Add_new_post_1.this)
                                .setTargetLength(512)
                                .setQuality(80)
                                .setOutputFormat("JPEG")
                                .setOutputFilename("resized_image4")
                                .setSourceImage(f)
                                .getResizedFile();
                        multipartTypedOutput.addPart("eventphotovideo[3]", new TypedFile("image/jpg", resizedImage3));
                    }


                    if (v4) {
                        File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video4));


                        multipartTypedOutput.addPart("eventphotovideo[3]", new TypedFile("video/mp4", imageFile));
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                boolean correct = true;
                if (country_spinnerET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_mobile_code));
                } else if (mobileET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.kindly_enter_mobile));
                } else if (country_spinner_Txt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_main_country));
                } else if (wtspcodeTxt.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_whatsap_code));
                } else if (wtsapMobile__ET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_whatsapp));
                } else if (snapET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_snapchat));
                } else if (instaET.getText().toString().equalsIgnoreCase("")) {
                    correct = false;
                    Helper.showToast(Add_new_post_1.this, getString(R.string.enter_insta));
                } else if (correct) {


                    progress = dialogUtil.showProgressDialog(Add_new_post_1.this, getString(R.string.please_wait));

                    appController.paServices.AddEventByCreator(multipartTypedOutput, new Callback<AddEventByCreatorRootDM>() {

                        @Override

                        public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
                            progress.dismiss();
                            if (addEventByCreatorRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {


                                EventId = addEventByCreatorRootDM.getOutput().getEventid();


                                if (iffree != false) {
                                    Helper.showToast(Add_new_post_1.this, getString(R.string.the_post_under_review));
                                    Add_new_post_1.this.finish();


                                }
                                if (ifpay != false) {
                                    Intent intent = new Intent(Add_new_post_1.this, Add_Event_Pay_Now.class);
                                    intent.putExtra("eventid", EventId);
                                    startActivity(intent);
                                }


                            } else
                                Helper.showToast(Add_new_post_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            progress.dismiss();
                            Log.e("error", retrofitError.toString());

                            Helper.showToast(Add_new_post_1.this, getString(R.string.no_package));

                        }
                    });

                }
            } else
                Helper.showToast(Add_new_post_1.this, getString(R.string.no_internet_connection));
        } else {
            Helper.showToast(Add_new_post_1.this, getString(R.string.kindly_select_terms));
        }

    }

    @OnClick(R.id.editpostnowBtn)
    public void Edit_Post() {
        EditPostByCoach();
    }

    public void EditPostByCoach() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            String id = String.valueOf(user.getId());

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));
            multipartTypedOutput.addPart("editstoryimgid[]", new TypedString(editimage0id));
            multipartTypedOutput.addPart("editeventimgid[]", new TypedString(editimage1id));
            multipartTypedOutput.addPart("eventdate", new TypedString(date));
            multipartTypedOutput.addPart("whatsapcountrycode", new TypedString(wtspcodeTxt.getText().toString()));
            multipartTypedOutput.addPart("whatsapnumber", new TypedString(wtsapMobile__ET.getText().toString()));
            multipartTypedOutput.addPart("snapchat", new TypedString(snapET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(instaET.getText().toString()));
            multipartTypedOutput.addPart("countryid[0]", new TypedString(CountryId));
            multipartTypedOutput.addPart("payorfree", new TypedString(payorfree));
            multipartTypedOutput.addPart("postedby", new TypedString(id));
            multipartTypedOutput.addPart("creatorcoach", new TypedString(user.getCreatorcoach()));
            multipartTypedOutput.addPart("posteddate", new TypedString(date));
            multipartTypedOutput.addPart("website", new TypedString(webET.getText().toString()));


            try {
                if (ifimg1) {
                    File f = new File(Add_new_post_1.this.getCacheDir(), "temp.jpg");
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
                    File resizedImage = new Resizer(Add_new_post_1.this)
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("image/jpg", resizedImage));
                }

                if (v1) {
                    File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video1));


                    multipartTypedOutput.addPart("eventphotovideo[0]", new TypedFile("video/mp4", imageFile));
                }

                if (ifimg2) {
                    File f = new File(Add_new_post_1.this.getCacheDir(), "temp1.jpg");
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
                    File resizedImage1 = new Resizer(Add_new_post_1.this)
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image2")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("image/jpg", resizedImage1));
                }

                if (v2) {
                    File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video2));


                    multipartTypedOutput.addPart("eventphotovideo[1]", new TypedFile("video/mp4", imageFile));
                }

                if (ifimg3) {
                    File f = new File(Add_new_post_1.this.getCacheDir(), "temp2.jpg");
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
                    File resizedImage2 = new Resizer(Add_new_post_1.this)
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image3")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[2]", new TypedFile("image/jpg", resizedImage2));
                }

                if (v3) {
                    File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video3));


                    multipartTypedOutput.addPart("eventphotovideo[2]", new TypedFile("video/mp4", imageFile));
                }
                if (ifimg4) {
                    File f = new File(Add_new_post_1.this.getCacheDir(), "temp3.jpg");
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
                    File resizedImage3 = new Resizer(Add_new_post_1.this)
                            .setTargetLength(512)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image4")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("eventphotovideo[3]", new TypedFile("image/jpg", resizedImage3));
                }


                if (v4) {
                    File imageFile = new File(getRealPathFromUri(Add_new_post_1.this, Video4));


                    multipartTypedOutput.addPart("eventphotovideo[3]", new TypedFile("video/mp4", imageFile));
                }
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }


            progress = dialogUtil.showProgressDialog(Add_new_post_1.this, getString(R.string.please_wait));

            appController.paServices.AddEventByCreator(multipartTypedOutput, new Callback<AddEventByCreatorRootDM>() {
                @Override
                public void success(AddEventByCreatorRootDM addEventByCreatorRootDM, Response response) {
                    progress.dismiss();
                    if (addEventByCreatorRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        //                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));
                        EventId = addEventByCreatorRootDM.getOutput().getEventid();

                        if (term != false) {


                            if (status != null) {
                                Helper.showToast(Add_new_post_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                            } else {
                                Intent intent = new Intent(Add_new_post_1.this, Add_Event_Pay_Now.class);
                                intent.putExtra("eventid", EventId);
                                startActivity(intent);
                            }
                        } else {
                            Helper.showToast(Add_new_post_1.this, getString(R.string.kindly_select_terms));
                        }

                    } else
                        Helper.showToast(Add_new_post_1.this, addEventByCreatorRootDM.getOutput().getMessage());

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Add_new_post_1.this, getString(R.string.no_internet_connection));
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

    @OnClick(R.id.vd1)
    public void Vd1() {
        imgClicked = 1;
        OpenImage();
    }

    @OnClick(R.id.vd2)
    public void Vd2() {
        imgClicked = 2;
        OpenImage();
    }

    @OnClick(R.id.vd3)
    public void Vd3() {
        imgClicked = 3;
        OpenImage();
    }

    @OnClick(R.id.vd4)
    public void Vd4() {
        imgClicked = 4;
        OpenImage();
    }

    public void OpenImage() {
        Dexter.withActivity(Add_new_post_1.this)
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(Add_new_post_1.this.getContentResolver(), uri);
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
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(Add_new_post_1.this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onTakeCameraSelectedVideo() {
                launchCameraIntentVideo();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        }, true);
    }

    private void launchCameraIntentVideo() {
//        Intent intent = new Intent(Add_new_post_1.this, ImagePickerActivity.class);
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

        Intent intent = new Intent(Add_new_post_1.this, CameraHandling.class);
        intent.putExtra("mode", "video");
        startActivityForResult(intent, IMAGE_VIDEO_ACTIVITY_PICKER);
    }

    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;
    int REQUEST_IMAGE_VIDEO = 998;

    private void launchCameraIntent() {
        Intent intent = new Intent(Add_new_post_1.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(Add_new_post_1.this, ImagePickerActivity.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_new_post_1.this);
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

}