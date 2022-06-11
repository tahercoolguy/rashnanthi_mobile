package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import com.master.design.rashnanthi.DataModel.AboutUsDM;
import com.master.design.rashnanthi.DataModel.Data;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    DialogUtil dialogUtil;
    Dialog progress;


    @BindView(R.id.about_AppTxt)
    TextView AboutAppTxt;

    public void AboutUs () {
        if (connectionDetector.isConnectingToInternet()) {
            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));

            appController.paServices.Aboutus(new Callback<AboutUsDM>() {
                @Override

                public void success(AboutUsDM aboutUsDM, Response response) {
                    progress.dismiss();

                    if (aboutUsDM.getOutput().getSuccess().equalsIgnoreCase("1"))

                        AboutAppTxt.setText(Html.fromHtml(String.valueOf(aboutUsDM.getOutput().getData().get(0).getContent()), Html.FROM_HTML_MODE_COMPACT));

                        //termAndCondition.setText(dataTerm.getItem().getDescription());

                    else
                        Helper.showToast(context, getString(R.string.something_wrong));
                }

                @Override
                public void failure( RetrofitError retrofitError) {
                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(context, getString(R.string.no_internet_connection));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
        dialogUtil = new DialogUtil();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.about_app_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);
            idMapping();
            AboutUs();

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
