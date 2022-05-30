package com.master.design.rashnanthi.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.EventRegisterDM;
import com.master.design.rashnanthi.DataModel.ProfilePictureRootDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.echodev.resizer.Resizer;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public class SignUpActivity extends AppCompatActivity {
    AppController appController;
    Context context;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int IMAGE_PICKER_SELECT1 = 2;
    private static final int FILE_PICKER_SELECT = 3;
    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;
    Uri uri;

    DialogUtil dialogUtil;
    String countryId;
    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    ProgressDialog progressDialog;

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

    @BindView(R.id.profile_RoundedImgView)
    CircleImageView profile_RoundedImgView;


    //    Spinner country_name_spinner, mobile_countrycode_Sp, wtsap_countrycode_Sp;

    ImageView   cameraImg, back_from_register_page;

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
//        progressDialog = new ProgressDialog(SignUpActivity.this);
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
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
         cameraImg = findViewById(R.id.cameraImg);
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        confirm_passwordET = findViewById(R.id.confirm_passwordET);
        snap_id_ET = findViewById(R.id.snap_id_ET);
        insta_id_ET = findViewById(R.id.insta_id_ET);
        mobileET = findViewById(R.id.mobileET);

        VisibilityFunction();


//                Intent i = new Intent(getApplicationContext(),Menu_1_Fragment.class);
//


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
                } else {
                    EventsCreatorAPI();
                }
            }
        });

        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImage();
            }
        });

    }


    boolean ifimg1 = true;

    public void EventsCreatorAPI() {
        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("fullname", new TypedString(nameET.getText().toString()));
            multipartTypedOutput.addPart("email", new TypedString(emailET.getText().toString()));
            multipartTypedOutput.addPart("password", new TypedString(passwordET.getText().toString()));
            multipartTypedOutput.addPart("confpassword", new TypedString(confirm_passwordET.getText().toString()));
            multipartTypedOutput.addPart("mobile", new TypedString(mobileET.getText().toString()));
            multipartTypedOutput.addPart("countrycode", new TypedString(country_spinnerET.getText().toString()));
            multipartTypedOutput.addPart("creatorcoach", new TypedString("1"));

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            multipartTypedOutput.addPart("deviceid", new TypedString(refreshedToken));
            multipartTypedOutput.addPart("devicetype", new TypedString("2"));
            try {
                if (ifimg1) {
                    File f = new File(context.getCacheDir(), "temp.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) profile_RoundedImgView.getDrawable()).getBitmap();
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
                    File resizedImage = new Resizer(context)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("image", new TypedFile("image/jpg", resizedImage));
                }


            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            appController.paServices.EventCreatorReg(multipartTypedOutput, new Callback<EventRegisterDM>() {
                @Override
                public void success(EventRegisterDM eventRegisterDM, Response response) {

                    if (eventRegisterDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        user.setId(Integer.valueOf(eventRegisterDM.getOutput().getUserid()));
                        user.setEmail(emailET.getText().toString());
                        //                            Helper.showToast(SignUpActivity.this, eventRegisterDM.getOutput().getMessage());

                        Intent intent = new Intent(SignUpActivity.this, VerifyActivity.class);
                        intent.putExtra("EventCreator", eventRegisterDM.getOutput().getCreatorcoach());
                        intent.putExtra("chirag1", "1");
                        intent.putExtra("mobile", eventRegisterDM.getOutput().getMobile());
                        startActivity(intent);
                        finish();

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
        if (connectionDetector.isConnectingToInternet()) {


            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("creatorcoach", new TypedString("2"));
            multipartTypedOutput.addPart("fullname", new TypedString(nameET.getText().toString()));
            multipartTypedOutput.addPart("email", new TypedString(emailET.getText().toString()));
            multipartTypedOutput.addPart("password", new TypedString(passwordET.getText().toString()));
            multipartTypedOutput.addPart("confpassword", new TypedString(confirm_passwordET.getText().toString()));
            multipartTypedOutput.addPart("mobile", new TypedString(mobileET.getText().toString()));
            multipartTypedOutput.addPart("countrycode", new TypedString(country_spinnerET.getText().toString()));
            multipartTypedOutput.addPart("snapchat", new TypedString(snap_id_ET.getText().toString()));
            multipartTypedOutput.addPart("instagram", new TypedString(insta_id_ET.getText().toString()));
                multipartTypedOutput.addPart("countryid", new TypedString(countryId));
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            multipartTypedOutput.addPart("deviceid", new TypedString(refreshedToken));
            multipartTypedOutput.addPart("devicetype", new TypedString("2"));
            try {
                if (ifimg1) {
                    File f = new File(context.getCacheDir(), "temp.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) profile_RoundedImgView.getDrawable()).getBitmap();
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
                    File resizedImage = new Resizer(context)
                            .setTargetLength(200)
                            .setQuality(80)
                            .setOutputFormat("JPEG")
                            .setOutputFilename("resized_image1")
                            .setSourceImage(f)
                            .getResizedFile();
                    multipartTypedOutput.addPart("image", new TypedFile("image/jpg", resizedImage));

                }


            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            appController.paServices.CoachReg(multipartTypedOutput, new Callback<EventRegisterDM>() {
                @Override
                public void success(EventRegisterDM eventRegisterDM, Response response) {
                     if (eventRegisterDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        user.setId(Integer.parseInt(eventRegisterDM.getOutput().getUserid()));
                        user.setEmail(emailET.getText().toString());


                        Intent intent = new Intent(SignUpActivity.this, VerifyActivity.class);
                        intent.putExtra("CoachCreator", eventRegisterDM.getOutput().getCreatorcoach());
                        intent.putExtra("chirag2", "2");
                        intent.putExtra("mobile", eventRegisterDM.getOutput().getMobile());
                        startActivity(intent);
                        finish();

//                            startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
//                            finish();
                    } else
                        Helper.showToast(SignUpActivity.this, eventRegisterDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError error) {
                     Log.e("String", error.toString());
                }
            });
        } else
            Helper.showToast(SignUpActivity.this, getString(R.string.no_internet_connection));

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

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();

    @OnClick(R.id.spinnerBottom_RL)
    public void Spinner_Country() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

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
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt.setText(data.get(position).getTitle());
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


    @OnClick(R.id.spinnerBottomRL)
    public void SpinnerCountry() {
        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinnerET.setText(data.get(position).getCallingcode());
//                country_Img.setImageResource(Integer.parseInt(data.get(position).getImage()));
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(mobilecountryImg);

                countryId = data.get(position).getId();
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
                            if (approvalOne.get(0).getId().equalsIgnoreCase("1")) {
                                country_spinnerET.setText(data.get(0).getCallingcode());
                                country_spinner_ET.setText(data.get(0).getCallingcode());
                                countryId= data.get(0).getId();
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(mobilecountryImg);
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(country_Img);
                            }
                        }
                    } else
                        Helper.showToast(SignUpActivity.this, "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
    }


    public void OpenImage() {
        Dexter.withActivity(((MainActivity) context))
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(SignUpActivity.this.getContentResolver(), uri);
                    if (imgClicked == 1) {
                        profile_RoundedImgView.setImageBitmap(bitmap);
                        ifimg1 = true;
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
        ImagePickerActivity.showImagePickerOptions(SignUpActivity.this, new ImagePickerActivity.PickerOptionListener() {
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
        Intent intent = new Intent(SignUpActivity.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(SignUpActivity.this, ImagePickerActivity.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
//            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }



}






