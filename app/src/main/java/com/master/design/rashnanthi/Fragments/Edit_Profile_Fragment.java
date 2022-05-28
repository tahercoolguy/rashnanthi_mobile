package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.ChangePasswordRootDM;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.UpdateProfileRootDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Edit_Profile_Fragment extends Fragment {
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    User user;
    DialogUtil dialogUtil;
    Dialog progress;

    private View rootView;
    private Context context;
    ImageView my_accountImg;
    RelativeLayout add_new_event_RL, view_event_RL, change_password_RL, edit_profile_RL;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    @BindView(R.id.name_ET)
    EditText name_ET;

    @BindView(R.id.email_ET)
    EditText email_ET;

    @BindView(R.id.mobileET)
    EditText mobileET;


    @BindView(R.id.country_spinnerET)
    EditText country_spinnerET;

    @BindView(R.id.update_Btn)
    Button update_Btn;

    @BindView(R.id.spinnerBottomRL)
    RelativeLayout spinnerBottomRL;

    @BindView(R.id.backImg)
    ImageView backImg;

    @BindView(R.id.countryImg)
    ImageView countryImg;


    @OnClick(R.id.backImg)
    public void Back() {
        ((MainActivity)context).addFragment(new My_Account_Fragment(),false);
    }

    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne=new ArrayList<>();
    ArrayList<String> approvalTwo=new ArrayList<>();

    @OnClick(R.id.spinnerBottomRL)
    public void Spinner_Country() {
        bottomForAll= new BottomForAll();
        bottomForAll.arrayList=approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinnerET.setText(data.get(position).getCallingcode());
//                countryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));

//                AreaID = data.get(selected).getId();
//                for (CountryData s:data
//                ) {
//                    if(s.getCallingcode().equals((String) object))
//                        AreaID = s.getId();
//                }


            }
        });


        bottomForAll.show(getParentFragmentManager(),"bottomSheetCountry");

//        bottomForAll.show(getSupportFragmentManager(), "bottomSheetCountry");
    }


    ArrayList<CountryData> data=new ArrayList<>();
    public void Binding()
    {
        if(connectionDetector.isConnectingToInternet())
        {
            appController.paServices.Countries(new Callback<CountryRootDM>() {
                @Override
                public void success(CountryRootDM countryRootDM, Response response) {
                    if(countryRootDM.getOutput().getSuccess().equalsIgnoreCase("1"))
                    {
                        data = countryRootDM.getOutput().getData();
                        for (CountryData area:countryRootDM.getOutput().getData()
                        ) {
                            approvalOne.add(area);
                        }
                    }else
                        Helper.showToast(context,"Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String",error.toString());
                }
            });
        }
    }

        @OnClick(R.id.update_Btn)
    public void Update_profile() {

            if(connectionDetector.isConnectingToInternet())
            {

//            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));

                String refreshedToken = FirebaseInstanceId.getInstance().getToken();



                appController.paServices.UpdateProfile(String.valueOf(user.getId()),name_ET.getText().toString(),email_ET.getText().toString(),mobileET.getText().toString(),country_spinnerET.getText().toString(), new Callback<UpdateProfileRootDM>() {
                    @Override

                    public void success ( UpdateProfileRootDM updateProfileRootDM, Response response )
                    {
//                    progress.dismiss();
                        if(updateProfileRootDM.getOutput().getSuccess().equalsIgnoreCase("1"))
                        {
                            Helper.showToast(getActivity(),"Updated Profile Successfully");

                        }else{
                            Helper.showToast(getActivity(),"Updated Profile Not Successfully");

                        }
                    }

                    @Override
                    public void failure ( RetrofitError retrofitError ) {
//                    progress.dismiss();
                        Log.e("error",retrofitError.toString());

                    }
                });
            }else
                Helper.showToast(context,getString(R.string.no_internet_connection));

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.edit_profile_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            context = getActivity();
            appController = (AppController) getActivity().getApplicationContext();
            user = new User(getActivity());

            connectionDetector = new ConnectionDetector(getActivity());
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage(getResources().getString(R.string.please_wait));
//            progressDialog.setIndeterminate(true);
//            progressDialog.setCancelable(false);
            ((MainActivity) context).setTitle(getString(R.string.change_password));

            Binding();
            idMapping();

            setClickListeners();
            setDetails();


        }
        return rootView;


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

