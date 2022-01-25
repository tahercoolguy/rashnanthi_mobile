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
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.master.design.rashnanthi.Activity.Add_new_post_1;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class My_Account_Fragment extends Fragment {

    private View rootView;
    private Context context;
    ImageView my_accountImg;
    RelativeLayout add_new_event_RL,view_event_RL,change_password_RL,edit_profile_RL;

//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;

//    @BindView(R.id.layout_parent) LinearLayout layout_parent;
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_account_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            my_accountImg=rootView.findViewById(R.id.my_accountImg);

            add_new_event_RL=rootView.findViewById(R.id.add_new_event_RL);
            view_event_RL=rootView.findViewById(R.id.view_event_RL);
            change_password_RL=rootView.findViewById(R.id.change_password_RL);
            edit_profile_RL=rootView.findViewById(R.id.edit_profile_RL);

            add_new_event_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(((MainActivity) context), Add_new_post_1.class));

                }
            });
            view_event_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            change_password_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            edit_profile_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            my_accountImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_2_Fragment(), false);

                }
            });
//            idMapping();
//
//            setClickListeners();
//            setDetails();

        }
        return rootView;
    }
//
//    private void idMapping() {
//
//
//    }
//
//    private void setClickListeners() {
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    private void setDetails() {
//       ShowProgress();
//        rootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               DismissProgress();
//            }
//        }, 1500);
//
//
//

//    }

//    public void ShowProgress()
//    {
//        progress_bar.setVisibility(View.VISIBLE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.GONE);
//    }
//
//    public void DismissProgress()
//    {
//        progress_bar.setVisibility(View.GONE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.VISIBLE);
//    }

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
