package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.LoginActivity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_MY_Event_1;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.DataModel.MyEventImageData;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.MyProfileRootDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class My_Account_Fragment extends Fragment {

    private View rootView;
    User user;
    private Context context;
    ImageView my_accountImg;
    RelativeLayout add_new_event_RL, view_event_RL, change_password_RL, edit_profile_RL;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.my_account_Img)
    ImageView my_account_Img;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

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
                    ((MainActivity) context).addFragment(new My_Event_1_Fragment(), false);


                }
            });
            change_password_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Change_Password_Fragment(), false);

                }
            });
            edit_profile_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Edit_Profile_Fragment(), false);

                }
            });



            MyProfileAPI();
            idMapping();

            setClickListeners();
            setDetails();


        }
        return rootView;


    }

    public void  MyProfileAPI(){

        if (connectionDetector.isConnectingToInternet()) {

             appController.paServices.MyProfile(String.valueOf(user.getId()),new Callback<MyProfileRootDM>() {
                 @Override
                 public void success(MyProfileRootDM myProfileRootDM, Response response) {
                     if (myProfileRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                         account_NameTxt.setText(myProfileRootDM.getOutput().getData().get(0).getFullname());
                         gmailTxt.setText(myProfileRootDM.getOutput().getData().get(0).getEmail());

//                         Picasso.get().load(myProfileRootDM.getOutput().getData().get(0).getProfilepic()).into((ImageView) rootView.findViewById(R.id.profileImg));


                     } else
                         Helper.showToast(getActivity(),"something wrong");
                 }

                 @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
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

        public void ShowProgress()
    {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress()
    {
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

