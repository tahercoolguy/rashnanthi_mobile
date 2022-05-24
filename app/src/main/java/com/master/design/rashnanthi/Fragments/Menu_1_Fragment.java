package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.master.design.rashnanthi.Activity.AddressSelector;
import com.master.design.rashnanthi.Activity.AdvertiseSelector;
import com.master.design.rashnanthi.Activity.LoginActivity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Menu_1_Fragment extends Fragment {

    private View rootView;
    private Context context;
    RelativeLayout languageRL;
    RelativeLayout aboutapp_RL;
    RelativeLayout registerRL;
    RelativeLayout contact_usRl;
    RelativeLayout loginRL;
    RelativeLayout myaccount_RL;
    RelativeLayout logout_Rl;
    RelativeLayout contact_uRL;
    User user;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    @BindView(R.id.term_conditionRL)
    RelativeLayout term_conditionRL;

    @BindView(R.id.privacy_policy_RL)
    RelativeLayout privacy_policy_RL;


    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
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
        ((MainActivity) context).setTitle(getString(R.string.home));

        String EventCreator = ((MainActivity) context).getIntent().getStringExtra("EventCreator");
        String CoachCreator = ((MainActivity) context).getIntent().getStringExtra("CoachCreator");
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.menu_1_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            idMapping();
            setClickListeners();
            setDetails();

            languageRL = rootView.findViewById(R.id.languageRL);
            aboutapp_RL = rootView.findViewById(R.id.aboutapp_RL);
            registerRL = rootView.findViewById(R.id.registerRL);
            loginRL = rootView.findViewById(R.id.loginRL);
            contact_usRl = rootView.findViewById(R.id.contact_usRl);
            myaccount_RL = rootView.findViewById(R.id.myaccount_RL);
            logout_Rl = rootView.findViewById(R.id.logout_Rl);


            if (user.getId() == 0) {
                registerRL.setVisibility(View.VISIBLE);
                loginRL.setVisibility(View.VISIBLE);
                myaccount_RL.setVisibility(View.GONE);
                logout_Rl.setVisibility(View.GONE);
            } else {
                registerRL.setVisibility(View.GONE);
                loginRL.setVisibility(View.GONE);
            }

            myaccount_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String eventcreator  = String.valueOf(user.getCoachOrEvent().equalsIgnoreCase("1"));
                    String coachcreator  = String.valueOf(user.getCoachOrEvent().equalsIgnoreCase("2"));


                    if(String.valueOf(user.getCoachOrEvent().equalsIgnoreCase("1"))==EventCreator){

                        ((MainActivity) context).addFragment(new My_Account_Fragment(), true);
                    }

                    else  {
                        ((MainActivity) context).addFragment(new Coach_Account_Fragment(), true);
                    }

//                    if (CoachCreator != null) {
//                        if (CoachCreator.equalsIgnoreCase("2")) {
//                            ((MainActivity) context).addFragment(new Coach_Account_Fragment(), true);
//
//                        }
//                    }else if (EventCreator != null) {
//                        if (EventCreator.equalsIgnoreCase("1")) {
//                            ((MainActivity) context).addFragment(new My_Account_Fragment(), true);
//
//                        }
//                    }




                }
            });
            logout_Rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.setId(0);
                    ((MainActivity) context).addFragment(new Calender_Fragment(), false);
                }
            });


            registerRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(((MainActivity) context), SignUpActivity.class);
                    startActivity(intent);

                }
            });

            languageRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new LanguageFragment(), true);


                }
            });
            aboutapp_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new About_App_Fragment(), true);


                }
            });


            loginRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(((MainActivity) context), LoginActivity.class);
                    startActivity(intent);
                }
            });

            contact_usRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((MainActivity) context).addFragment(new Contact_Us_Fragment(), true);


                }
            });

            term_conditionRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((MainActivity) context).addFragment(new Term_And_Condition_Fragment(), true);


                }
            });

            privacy_policy_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((MainActivity) context).addFragment(new Privacy_Policy_Fragment(), true);


                }
            });


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
