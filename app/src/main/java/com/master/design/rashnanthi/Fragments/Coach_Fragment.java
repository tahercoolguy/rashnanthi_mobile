package com.master.design.rashnanthi.Fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SpinneerActivity;
import com.master.design.rashnanthi.Adapter.ImageRecyclerAdapter;
import com.master.design.rashnanthi.Adapter.ImageRecyclerAdapter1;
import com.master.design.rashnanthi.Adapter.SliderPagerAdapter1;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachDM;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.DataModel.NewCoachDataModel;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

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

public class Coach_Fragment extends Fragment {

    private View rootView;
    private Context context;
    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog progress;
    public ArrayList<CoachDM> coachDMArrayList;
    //    public ArrayList<County_ItemDM> county_itemDMS;
//    Spinner calender_page_country_spinner;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;
    private ImageRecyclerAdapter adapter;
    private ImageRecyclerAdapter1 adapter1;
    private List<String> mList;


    RecyclerView coach_Rcv;
    RelativeLayout coach_grid_account;


    @BindView(R.id.spinnerCountryBottomRL)
    LinearLayout spinnerCountryBottomRL;


    @BindView(R.id.countryImg)
    ImageView countryImg;


    @BindView(R.id.coachgridImg)
    ImageView coachgridImg;

    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;


    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    ProgressDialog progressDialog;
    ImageView coach_menu_Back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();
        user = new User(context);
        appController = (AppController) getApplicationContext();
        dialogUtil = new DialogUtil();
        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));
        Binding();


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.coach, container, false);
            ButterKnife.bind(this, rootView);
            coach_Rcv = rootView.findViewById(R.id.coach_Rcv);
//            coach_menu = rootView.findViewById(R.id.coach_menu);
            coach_grid_account = rootView.findViewById(R.id.coach_grid_account);

            Glide.with(context)
                    .asGif()
                    .load(R.raw.coach_grid_celib)
                    .into(coachgridImg);

            coach_menu_Back = rootView.findViewById(R.id.coach_menu_Back);

            coach_menu_Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);
                }
            });


//
//            Adapter_Country_Spinner adapter_country_spinner;
//
//            adapter_country_spinner = new Adapter_Country_Spinner(context, county_itemDMS);
//
//
//            calender_page_country_spinner.setAdapter(adapter_country_spinner);


//            whatsapp_IMg=rootView.findViewById(R.id.whatsapp_IMg);

//            whatsapp_IMg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

//            insta_img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//            snapchaht_Img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

            coach_grid_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((MainActivity) context).addFragment(new Coach_Grid_Account_Fragment(), false);


                }
            });


//            coach_menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

            coachDMArrayList = new ArrayList<>();


            mList = new ArrayList<>();
            //get data from backend
            mList.add("one");
            mList.add("two");
            mList.add("three");


            APIforCoach("1");

            setDetails();

        }
        return rootView;
    }

    public void APIforCoach(String countryid) {
        if (connectionDetector.isConnectingToInternet()) {
            progress = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));
            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("countryid", new TypedString(countryid));
//

            appController.paServices.GetAllCoachesWithPostsNew(multipartTypedOutput, new Callback<NewCoachDataModel>() {
                @Override
                public void success(NewCoachDataModel newCoachDataModel, Response response) {
                    progress.dismiss();
                    if (newCoachDataModel.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        context = getActivity();
                        adapter1 = new ImageRecyclerAdapter1(getActivity(), newCoachDataModel.getOutput().getData());
                        coach_Rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_anim);
                        coach_Rcv.startAnimation(animation);
                        coach_Rcv.setHasFixedSize(true);
                        coach_Rcv.setAdapter(adapter1);
                    } else
                        Helper.showToast(context, getString(R.string.no_posts));
                }

                @Override
                public void failure(RetrofitError error) {
                    progress.dismiss();
                }
            });

        }
    }



    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


    @OnClick(R.id.spinnerCountryBottomRL)
    public void Spinnercountry() {


        startActivityForResult(new Intent(context, SpinneerActivity.class), 48);

    }


    String countryname;
    String countryimg;
    String countryid = "1";

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = data;
        if (data != null) {
            countryname = data.getStringExtra("countryname");
            countryimg = data.getStringExtra("countryimg");
            countryid = data.getStringExtra("countryid");
            Picasso.get().load(AppController.base_image_url + countryimg).into(countryImg);
            country_spinner_Txt.setText(countryname);

            APIforCoach(countryid);
        }
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
                                if (user.getLanguageCode().equalsIgnoreCase("en")) {
                                    country_spinner_Txt.setText(data.get(0).getTitle());

                                } else {
                                    country_spinner_Txt.setText(data.get(0).getTitlear());

                                }
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(countryImg);
                            }
                        }
                    } else
                        Helper.showToast(getActivity(), getString(R.string.something_wrong));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
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
        }, 100);


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
