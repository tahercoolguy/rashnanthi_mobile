package com.master.design.rashnanthi.Fragments;

import static com.master.design.rashnanthi.Controller.AppController.TAG;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.LoginActivity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_Bottom;
import com.master.design.rashnanthi.Adapter.Adapter_Coach_Fgmt;
import com.master.design.rashnanthi.Adapter.Adapter_Coach__grid_Fgmt;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachGridDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.DataModel.GetCoachByCountryData;
import com.master.design.rashnanthi.DataModel.GetCoachsByCountryRootDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.ResponseListener1;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Coach_Grid_Account_Fragment extends Fragment {

    private View rootView;
    private Context context;
    RecyclerView my_account_grid_Rcv;
    User user;
    //     ImageView coach_menu,like_coach_grid;
    private ArrayList<CoachGridDM> coachGridDMArrayList;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;

    ImageView coach_menu_Back;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog dialog;


    String CountryId;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;


    @BindView(R.id.spinnerCountryBottomRL)
    RelativeLayout spinnerCountryBottomRL;


    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.countryImg)
    ImageView countryImg;

    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;


    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();
        user = new User(getActivity());
        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        //        coach_menu =rootView.findViewById(R.id.coach_menu);
//
//        coach_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) context).addFragment(new Menu_1_Fragment() , false);
//
//            }
//        });


//        like_coach_grid=rootView.findViewById(R.id.like_coach_grid);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.coach_grid_account_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            setDetails();

            Binding();

            my_account_grid_Rcv = rootView.findViewById(R.id.my_account_grid_Rcv);
            coach_menu_Back = rootView.findViewById(R.id.coach_menu_Back);

            coach_menu_Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);
                }
            });




        }
        return rootView;
    }


    public void GetCoachsByCountry(String Id) {

        if (connectionDetector.isConnectingToInternet()) {
//            dialog = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));
            appController.paServices.GetCoachsByCountry(Id, new Callback<GetCoachsByCountryRootDM>() {
                @Override
                public void success(GetCoachsByCountryRootDM getCoachsByCountryRootDM, Response response) {
//                    dialog.dismiss();
                    if (getCoachsByCountryRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        if (Id != null) {

                            try {
                                my_account_grid_Rcv.setVisibility(View.VISIBLE);
                                Adapter_Coach__grid_Fgmt occasionAdapter = new Adapter_Coach__grid_Fgmt(getActivity(), getCoachsByCountryRootDM.getOutput().getData());
                                GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);
                                my_account_grid_Rcv.setLayoutManager(linearLayoutManager);
                                my_account_grid_Rcv.setAdapter(occasionAdapter);
                            }   catch(Exception error1) {
                                Log.e(TAG, "The exception caught while executing the process. (error1)");
                                error1.printStackTrace();

                            }

                        }
                    } else {
                        my_account_grid_Rcv.setVisibility(View.GONE);
                        Helper.showToast(getActivity(), "coachs does not exist");

                    }


                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    dialog.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
    }

    BottomForAll bottomForAll;
    Adapter_Bottom adapter_bottom;
    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


    @OnClick(R.id.spinnerCountryBottomRL)
    public void SpinnerCountry() {

        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                String Id = approvalOne.get(position).getId();

                if (Id == data.get(position).getId()) {
                    GetCoachsByCountry(Id);

                } else {
                    Helper.showToast(context, "Country user does not exist ");
//                    my_account_grid_Rcv.setVisibility(View.GONE);
                }
                country_spinner_Txt.setText(data.get(position).getTitle());
                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(countryImg);

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
                            GetCoachsByCountry(approvalOne.get(0).getId());
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
