package com.master.design.rashnanthi.Fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.ImageRecyclerAdapter;
import com.master.design.rashnanthi.Adapter.SliderAdapter;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.EventsDetailsRootDM;
import com.master.design.rashnanthi.DataModel.GetEventsByCountryDateRootDM;
import com.master.design.rashnanthi.DataModel.SliderData;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

public class Event_Small_Image_Fragment extends Fragment {

    private View rootView;
    private Context context;

    ImageView even_back_Img, menu_1_menu;
    DialogUtil dialogUtil;
    Dialog dialog;
    String countryIdMain = "1";

    String Snapchat, Instagram, Whatsapp, Website, WhatsappCountryCode;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.slider)
    SliderView slider;

    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.wtsapImg)
    ImageView wtsapImg;

    @BindView(R.id.instaImg)
    ImageView instaImg;

    @BindView(R.id.webImg)
    ImageView webImg;

    @BindView(R.id.snapImg)
    ImageView snapImg;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;


    @OnClick(R.id.wtsapImg)
    public void whatSapp() {
//        String url = "https://api.whatsapp.com/send?phone=" + WhatsappCountryCode + Whatsapp;
//        try {
//            PackageManager pm = context.getPackageManager();
//            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            context.startActivity(i);
//        } catch (PackageManager.NameNotFoundException e) {
//             Helper.showToast(context, "Whatsapp app not installed in your phone");
//
//            e.printStackTrace();
//        }
        try {
            String url = "https://api.whatsapp.com/send?phone=" + WhatsappCountryCode + Whatsapp;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            Helper.showToast(context, context.getString(R.string.something_wrong));
        }
    }


    @OnClick(R.id.instaImg)
    public void Insta() {
        Uri uri = Uri.parse("http://instagram.com/" + Instagram);


        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        i.setPackage("com.instagram.android");

        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/" + Instagram)));
        }
    }


    @OnClick(R.id.webImg)
    public void Web() {
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + Website));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {

            Helper.showToast(context, "No application can handle this request."
                    + " Please install a web browser");
            e.printStackTrace();
        }
    }

    @OnClick(R.id.snapImg)
    public void Snap() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + Snapchat));
            intent.setPackage("com.snapchat.android");
            startActivity(intent);
        } catch (Exception e) {
            Helper.showToast(context, getString(R.string.snapchat_not_installed));


            e.printStackTrace();
        }
    }

    User user;

    String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        dialogUtil = new DialogUtil();

        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.event_small_image_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);

            setClickListeners();
            setDetails();
            even_back_Img = rootView.findViewById(R.id.even_back_Img);
            menu_1_menu = rootView.findViewById(R.id.menu_1_menu);
            user = new User(getActivity());
            even_back_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Calender_Fragment(), false);
                }
            });

            menu_1_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), true);
                }
            });
            Bundle bd = getArguments();
            date = bd.getString("date");
            countryIdMain = bd.getString("countryid");
            setClickListeners();
            setDetails();
            newAPI();


        }
        return rootView;
    }


    private void setClickListeners() {

    }


    public void DataNUll() {
        if (Instagram == null) {
            instaImg.setVisibility(View.GONE);
        } else {
            instaImg.setVisibility(View.VISIBLE);
        }


        if (Whatsapp == null) {
            wtsapImg.setVisibility(View.GONE);
        } else {
            wtsapImg.setVisibility(View.VISIBLE);
        }


        if (Website == null) {
            webImg.setVisibility(View.GONE);
        } else {
            webImg.setVisibility(View.VISIBLE);
        }


        if (Snapchat == null) {
            snapImg.setVisibility(View.GONE);
        } else {
            snapImg.setVisibility(View.VISIBLE);
        }

    }

    public void newAPI() {
        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("countryid", new TypedString(countryIdMain));
            multipartTypedOutput.addPart("date", new TypedString(date));


            appController.paServices.GetEventsByCountryDate(multipartTypedOutput, new Callback<GetEventsByCountryDateRootDM>() {
                @Override
                public void success(GetEventsByCountryDateRootDM getEventsByCountryDateRootDM, Response response) {
                    if (getEventsByCountryDateRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

//                        EventDate = getEventsByCountryDateRootDM.getOutput().getData().get(0).getEventdate();


//                            Instagram = getEventsByCountryDateRootDM.getOutput().getData().get(0).getInstagram();
//                            Whatsapp = getEventsByCountryDateRootDM.getOutput().getData().get(0).getWhatsapnumber();
//                            Snapchat = getEventsByCountryDateRootDM.getOutput().getData().get(0).getSnapchat();
//                            Website = getEventsByCountryDateRootDM.getOutput().getData().get(0).getWebsite();
//                            WhatsappCountryCode = getEventsByCountryDateRootDM.getOutput().getData().get(0).getWhatsapcountrycode();
//                            DataNUll();

                        // passing this array list inside our adapter class.
                        if (getEventsByCountryDateRootDM.getOutput().getData().get(0).getImage() != null ||
                                getEventsByCountryDateRootDM.getOutput().getData().get(0).getImagedata() != null) {

                            SliderAdapter adapter = new SliderAdapter(context, getEventsByCountryDateRootDM.getOutput().getData());

                            // below method is used to set auto cycle direction in left to
                            // right direction you can change according to requirement.
                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_anim);
                            slider.startAnimation(animation);
                            slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                            // below method is used to
                            // setadapter to sliderview.
                            slider.setSliderAdapter(adapter);
                            slider.setInfiniteAdapterEnabled(false);
                            // below method is use to set
                            // scroll time in seconds.

                            slider.setScrollTimeInSec(3);
//
//                        // to set it scrollable automatically
//                        // we use below method.

//                            slider.setAutoCycle(true);
//
//                        // to start autocycle below method is used.

//                            slider.startAutoCycle();

                        } else {
                            Helper.showToast(getActivity(), getString(R.string.no_posts));
                        }
                    } else {
                        Helper.showToast(getActivity(), getString(R.string.some_netork_happened));
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        } else {
            Helper.showToast(context, getString(R.string.no_internet_connection));

        }
    }
//    public void EventDetailsAPI() {
//
//        if (connectionDetector.isConnectingToInternet()) {
//             appController.paServices.EventDetails("15", new Callback<EventsDetailsRootDM>() {
//                @Override
//                public void success(EventsDetailsRootDM eventsDetailsRootDM, Response response) {
//                     if (eventsDetailsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
//                        Instagram = eventsDetailsRootDM.getOutput().getData().get(0).getInstagram();
//                        Whatsapp = eventsDetailsRootDM.getOutput().getData().get(0).getWhatsapnumber();
//                        Snapchat = eventsDetailsRootDM.getOutput().getData().get(0).getSnapchat();
//                        Website = eventsDetailsRootDM.getOutput().getData().get(0).getWebsite();
//                        WhatsappCountryCode = eventsDetailsRootDM.getOutput().getData().get(0).getWhatsapcountrycode();
//
//                        // passing this array list inside our adapter class.
//                        SliderAdapter adapter = new SliderAdapter(context, eventsDetailsRootDM.getOutput().getData());
//
//                        // below method is used to set auto cycle direction in left to
//                        // right direction you can change according to requirement.
//                        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//
//                        // below method is used to
//                        // setadapter to sliderview.
//                        slider.setSliderAdapter(adapter);
//
//                        // below method is use to set
//                        // scroll time in seconds.
//
//                        slider.setScrollTimeInSec(3);
////
////                        // to set it scrollable automatically
////                        // we use below method.
//
//                        slider.setAutoCycle(true);
////
////                        // to start autocycle below method is used.
//
//                        slider.startAutoCycle();
//
//
//
//
//                    } else
//                        Helper.showToast(context, "event details does not exist here ");
//                }
//
//                @Override
//                public void failure(RetrofitError retrofitError) {
//                     Log.e("String", retrofitError.toString());
//
//                }
//            });
//        } else
//            Helper.showToast(context, getString(R.string.no_internet_connection));
//
//    }

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

//    private void idMapping1() {
//        // Urls of our images.
//        String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
//        String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
//        String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";
//
//        // we are creating array list for storing our image urls.
//        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
//
//
//        // adding the urls inside array list
//        sliderDataArrayList.add(new SliderData(url1));
//        sliderDataArrayList.add(new SliderData(url2));
//        sliderDataArrayList.add(new SliderData(url3));
//
//        // passing this array list inside our adapter class.
////        SliderAdapter adapter = new SliderAdapter(context, sliderDataArrayList);
//
//        // below method is used to set auto cycle direction in left to
//        // right direction you can change according to requirement.
//        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//
//        // below method is used to
//        // setadapter to sliderview.
////        slider.setSliderAdapter(adapter);
//
//        // below method is use to set
//        // scroll time in seconds.
//        slider.setScrollTimeInSec(3);
//
//        // to set it scrollable automatically
//        // we use below method.
//        slider.setAutoCycle(true);
//
//        // to start autocycle below method is used.
//        slider.startAutoCycle();
//    }

}
