package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class About_App_Fragment extends Fragment {

    private View rootView;
    private Context context;
    ImageView about_appp_menu;
    ImageView back_menu;



    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;


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
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.about_app_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();

            setClickListeners();
            setDetails();
            about_appp_menu=rootView.findViewById(R.id.about_appp_menu);
            back_menu=rootView.findViewById(R.id.back_menu);

            back_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);

                }
            });

            about_appp_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);

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
