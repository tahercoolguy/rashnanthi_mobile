package com.master.design.rashnanthi.Fragments;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.ImagePickerActivity;
import com.master.design.rashnanthi.Activity.LoginActivity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_MY_Event_1;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.DataModel.MyEventImageData;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.MyProfileRootDM;
import com.master.design.rashnanthi.DataModel.ProfilePictureRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import it.sephiroth.android.library.widget.HListView;
import me.echodev.resizer.Resizer;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public class My_Account_Fragment extends Fragment {

    private View rootView;
    User user;
    private Context context;
    ImageView my_accountImg;
    RelativeLayout add_new_event_RL, view_event_RL, change_password_RL, edit_profile_RL;
    DialogUtil dialogUtil;
    Dialog progress;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.my_account_Img)
    CircleImageView my_account_Img;

    @BindView(R.id.edit_ProfileImg)
    ImageView edit_ProfileImg;

    @BindView(R.id.account_NameTxt)
    TextView account_NameTxt;

    @BindView(R.id.gmailTxt)
    TextView gmailTxt;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    LinearLayout ad_more_eventtLL, website_LL;
    Button add_more_eventBtn;
    TextView your_post_will_beTXt;
    RadioButton radioBtn_Term_condition;
    Button pay_now_Btn, continue_Btn;
    ImageView coach_menu_Back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();
        dialogUtil = new DialogUtil();

        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_account_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            user = new User(getActivity());
            coach_menu_Back = rootView.findViewById(R.id.coach_menu_Back);

            coach_menu_Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);
                }
            });

            my_accountImg = rootView.findViewById(R.id.my_accountImg);

            my_accountImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);

                }
            });
            add_new_event_RL = rootView.findViewById(R.id.add_new_event_RL);
            view_event_RL = rootView.findViewById(R.id.view_event_RL);
            change_password_RL = rootView.findViewById(R.id.change_password_RL);
            edit_profile_RL = rootView.findViewById(R.id.edit_profile_RL);


            add_new_event_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(((MainActivity) context), Activity_Add_Event_1.class));

                }
            });
            view_event_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new My_Event_1_Fragment(), true);


                }
            });
            change_password_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Change_Password_Fragment(), true);

                }
            });
            edit_profile_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Edit_Profile_Fragment(), true);

                }
            });


            MyProfileAPI();
            idMapping();

            setClickListeners();
            setDetails();


        }
        return rootView;


    }

    boolean ifimg1 = false;

    @OnClick(R.id.edit_ProfileImg)
    public void ProfilePictureAPI() {
        OpenImage();
    }


    public void EditProfileImageAPI() {
        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("userid", new TypedString(String.valueOf(user.getId())));

            try {
                if (ifimg1) {
                    File f = new File(context.getCacheDir(), "temp.jpg");
                    f.createNewFile();

                    Bitmap one = ((BitmapDrawable) my_account_Img.getDrawable()).getBitmap();
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
                            .setTargetLength(512)
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
            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));

            appController.paServices.ProfilePicture(multipartTypedOutput, new Callback<ProfilePictureRootDM>() {
                @Override
                public void success(ProfilePictureRootDM profilePictureRootDM, Response response) {
                    progress.dismiss();

                    if (profilePictureRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Helper.showToast(getActivity(), profilePictureRootDM.getOutput().getMessage());
                    } else
                        Helper.showToast(getActivity(), profilePictureRootDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
    }

    public void MyProfileAPI() {

        if (connectionDetector.isConnectingToInternet()) {

            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));

            appController.paServices.MyProfile(String.valueOf(user.getId()), new Callback<MyProfileRootDM>() {
                @Override
                public void success(MyProfileRootDM myProfileRootDM, Response response) {
                    progress.dismiss();

                    if (myProfileRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        account_NameTxt.setText(myProfileRootDM.getOutput().getData().get(0).getFullname());
                        gmailTxt.setText(myProfileRootDM.getOutput().getData().get(0).getEmail());
                        Picasso.get().load(AppController.base_image_url + myProfileRootDM.getOutput().getData().get(0).getProfilepic()).into(my_account_Img);


//                         Picasso.get().load(myProfileRootDM.getOutput().getData().get(0).getProfilepic()).into((ImageView) rootView.findViewById(R.id.profileImg));


                    } else
                        Helper.showToast(getActivity(), getString(R.string.something_wrong));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

                    my_account_Img.setImageBitmap(bitmap);
                    ifimg1 = true;
                    EditProfileImageAPI();

                    // loading profile image from local cache

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getContext(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onTakeCameraSelectedVideo() {

            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        },false);
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
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
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
//            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

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

    public void ShowProgress() {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress() {
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

