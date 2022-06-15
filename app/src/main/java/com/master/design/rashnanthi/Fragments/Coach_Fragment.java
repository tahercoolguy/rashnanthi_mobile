package com.master.design.rashnanthi.Fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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
import com.master.design.rashnanthi.Adapter.ImageRecyclerAdapter;
import com.master.design.rashnanthi.Adapter.ImageRecyclerAdapter1;
import com.master.design.rashnanthi.Adapter.SliderPagerAdapter1;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachDM;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
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
    RelativeLayout spinnerCountryBottomRL;


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
                    .load(R.raw.icon_1)
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

//
//            ArrayList<CoachDM> coachDMArrayList = new ArrayList<>();
//
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//            coachDMArrayList.add(new CoachDM(R.drawable.coach_slider_img));
//
//
//            coach_Rcv.setLayoutManager(new LinearLayoutManager((MainActivity) context));
//            coach_Rcv.setAdapter(new Adapter_Coach_Fgmt(((MainActivity) context), coachDMArrayList));




            mList=new ArrayList<>();
             //get data from backend
            mList.add("one");
            mList.add("two");
            mList.add("three");


            APIforCoach("1");

//            adapter1 =new ImageRecyclerAdapter1(mList,getContext(), "1");
//            coach_Rcv.setLayoutManager(new LinearLayoutManager(getContext()));
//            coach_Rcv.setHasFixedSize(true);
//            coach_Rcv.setAdapter(adapter1);
//            adapter1.notifyDataSetChanged();



//            Adapter_Country_Spinner adapter_country_spinner;
//
//            adapter_country_spinner = new Adapter_Country_Spinner(context, county_itemDMS);
//
//
//            calender_page_country_spinner.setAdapter(adapter_country_spinner);


//
//
//            // we are creating array list for storing our image urls.
//            ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
//
//            // initializing the slider view.
//            SliderView sliderView = rootView.findViewById(R.id.slider);
//
//            // adding the urls inside array list
//            sliderDataArrayList.add(new SliderData(R.drawable.images));
//            sliderDataArrayList.add(new SliderData(R.drawable.images));
//            sliderDataArrayList.add(new SliderData(R.drawable.images));
//            // passing this array list inside our adapter class.
//            SliderAdapter adapter = new SliderAdapter(context, sliderDataArrayList);
//
//            // below method is used to set auto cycle direction in left to
//            // right direction you can change according to requirement.
//            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//
//            // below method is used to
//            // setadapter to sliderview.
//            sliderView.setSliderAdapter(adapter);
//
//            // below method is use to set
//            // scroll time in seconds.
//            sliderView.setScrollTimeInSec(3);
//
//            // to set it scrollable automatically
//            // we use below method.
//            sliderView.setAutoCycle(true);
//
//            // to start autocycle below method is used.
//            sliderView.startAutoCycle();

            setDetails();

        }
        return rootView;
    }

    public  void APIforCoach(String id)
    {
        if (connectionDetector.isConnectingToInternet()) {
           progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));
            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("countryid", new TypedString(id));
            appController.paServices.GetAllCoachesWithPosts(multipartTypedOutput, new Callback<CoachesWithPostsRootDM>() {
                @Override
                public void success(CoachesWithPostsRootDM coachesWithPostsRootDM, Response response) {
                    progress.dismiss();
                    if (coachesWithPostsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        context = getActivity();
                        adapter1 =new ImageRecyclerAdapter1(getActivity(),coachesWithPostsRootDM.getOutput().getData() );
                        coach_Rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        coach_Rcv.setHasFixedSize(true);
                        coach_Rcv.setAdapter(adapter1);
                    } else
                        Helper.showToast(context, getString(R.string.some_netork_happened));
                }

                @Override
                public void failure(RetrofitError error) {
                    progress.dismiss();

                    Log.e("String", error.toString());
                }
            });
        }
    }



    public void GetAllCoachesWithPosts(){

        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("countryid", new TypedString("1"));
            progress = dialogUtil.showProgressDialog(context, getString(R.string.please_wait));


            appController.paServices.GetAllCoachesWithPosts(multipartTypedOutput,new Callback<CoachesWithPostsRootDM>() {
                @Override
                public void success(CoachesWithPostsRootDM coachesWithPostsRootDM, Response response) {
                    progress.dismiss();
                    if (coachesWithPostsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                    } else
                        Helper.showToast(getActivity(), "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    progress.dismiss();

                    Log.e("String", error.toString());
                }
            });
        }
    }




    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


    @OnClick(R.id.spinnerCountryBottomRL)
    public void SpinnerCountry() {

        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt.setText(data.get(position).getTitle());
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(countryImg);

                APIforCoach(data.get(position).getId());



            }
        });


        bottomForAll.show(getParentFragmentManager(), "bottomSheetCountry");
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
                                country_spinner_Txt.setText(data.get(0).getTitle());
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(countryImg);
                            }
                        }
                    } else
                        Helper.showToast(getActivity(), "Some network happened ..");
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
